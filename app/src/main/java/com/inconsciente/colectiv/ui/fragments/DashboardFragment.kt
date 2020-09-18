package com.inconsciente.colectiv.ui.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inconsciente.colectiv.R
import com.inconsciente.colectiv.service.ConfigService
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DashboardFragment : Fragment() {
    var START_MILLI_SECONDS = 60000L

    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false;
    var time_in_milli_seconds = 0L
    var time = 10000L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val service = ConfigService(requireContext())
            withContext(Dispatchers.Main){
                time_in_milli_seconds = service.millisecondsToNextOffer()
                startTimer(time_in_milli_seconds)

            }
        }
    }


    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                timer.text = "a comprar"
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }
        }
        countdown_timer.start()

        isRunning = true


    }

    private fun updateTextUI() {
        val seconds = time_in_milli_seconds / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        val text = "${days} días ${hours}:${minutes}:${seconds}"
        timer.text = text
    }



}