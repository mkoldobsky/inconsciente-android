package com.inconsciente.colectiv.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.inconsciente.colectiv.R
import com.inconsciente.colectiv.database.Config
import com.inconsciente.colectiv.database.getDatabase
import com.inconsciente.colectiv.network.InconscienteApi
import com.inconsciente.colectiv.network.Prospect
import com.inconsciente.colectiv.repository.InconscienteRepository
import com.inconsciente.colectiv.service.ConfigService
import com.inconsciente.colectiv.ui.NavigationHost
import kotlinx.android.synthetic.main.zipcode_fragment.*
import kotlinx.android.synthetic.main.zipcode_fragment.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber

class ZipcodeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        val view = inflater.inflate(R.layout.zipcode_fragment, container, false)

        val validateButton = view.validate_button
        val nextButton = view.next_button
        val messageTextView = view.message_text_view
        val nameMailLayout = view.name_mail_layout
        val nameTextView = view.name_edit_text
        val emailTextView = view.email_edit_text
        val sendMailButton = view.send_mail_button
        CoroutineScope(Dispatchers.IO).launch {
            val repository = InconscienteRepository(getDatabase(view.context))
            val config = repository.getConfig()
            if (config != null && config.zipcode != "nozipcode") {
                withContext(Dispatchers.Main) {
                    next()
                }
            }
        }


        nextButton.setOnClickListener {
            next()
        }
        validateButton.setOnClickListener {
            val zipcode = zipcode_edit_text.text
            if (!isValidZipcode(zipcode!!)) {
                zipcode_text_input.error = getString(R.string.zipcode_error)
            } else {
                zipcode_text_input.error = null

                CoroutineScope(Dispatchers.IO).launch {
                    val configService = ConfigService(view.context)
                    configService.refreshConfigFromNetwork()
                    val area = configService.getAreaFromZipcode(zipcode.toString())
                    configService.updateConfigWithZipcode(zipcode.toString())

                    withContext(Dispatchers.Main){
                    var message = "Algo salió mal!"
                    try {
                        if (area.toString() != "noarea") {
                            validateButton.visibility = View.INVISIBLE
                            nextButton.visibility = View.VISIBLE

                            message = "area ${area.toString()}"
                            messageTextView.text = getString(R.string.congrats_message)
                            messageTextView.visibility = View.VISIBLE

                            Timber.i(message)
                            Toast.makeText(view.context, message, Toast.LENGTH_LONG)
                        } else {
                            messageTextView.text = getString(R.string.no_area_message)
                            messageTextView.visibility = View.VISIBLE
                            validateButton.visibility = View.INVISIBLE
                            nameMailLayout.visibility = View.VISIBLE

                        }
                    } catch (e: HttpException) {
                        message = "Exception ${e.message}"
                    }
                    Toast.makeText(view.context, message, Toast.LENGTH_LONG)
                }
                }
            }


        }

        sendMailButton.setOnClickListener{
            val zipcode = zipcode_edit_text.text
            val name = nameTextView.text
            val email = emailTextView.text
            CoroutineScope(Dispatchers.IO).launch {
                val prospect = Prospect(zipcode.toString(), name.toString(), email.toString())
                InconscienteApi.retrofitService.postProspect(prospect)
                withContext(Dispatchers.Main) {
                    var message = "Algo salió mal!"
                    try {

                        next()

                    } catch (e: HttpException) {
                        message = "Exception ${e.message}"
                    }
                    Toast.makeText(view.context, message, Toast.LENGTH_LONG)
                }
            }
        }

        return view
    }

    private fun next() {
        (activity as NavigationHost).navigateTo(MessageFragment(), false)
    }

    private fun isValidZipcode(zipcodeText: Editable?): Boolean {
        return zipcodeText?.length == 4
    }

}