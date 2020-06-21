package com.inconsciente.colectiv.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inconsciente.colectiv.R
import com.inconsciente.colectiv.ui.NavigationHost
import kotlinx.android.synthetic.main.zipcode_fragment.*
import kotlinx.android.synthetic.main.zipcode_fragment.view.*

class ZipcodeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        val view = inflater.inflate(R.layout.zipcode_fragment, container, false)

        view.next_button.setOnClickListener {
            if (!isValidZipcode(zipcode_edit_text.text!!)) {
                zipcode_text_input.error = getString(R.string.zipcode_error)
            } else {
                // Clear the error.
                zipcode_text_input.error = null
                // Navigate to the next Fragment.
                (activity as NavigationHost).navigateTo(MessageFragment(), false)
            }
        }


        return view
    }

    private fun isValidZipcode(zipcodeText: Editable?): Boolean {
            return zipcodeText?.length == 4
    }

}