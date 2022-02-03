package com.example.movieapp.ui.threads

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentThreadsBinding
import com.example.movieapp.services.BoundService
import com.example.movieapp.services.ForegroundService
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit


class ThreadsFragment : Fragment(), CoroutineScope by MainScope() {
    private var _binding: FragmentThreadsBinding? = null
    private val binding get() = _binding!!

    private var isBound = false
    private var boundService: BoundService.ServiceBinder? = null

    private val testReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(
                context,
                "FROM SERVICE: ${intent?.getBooleanExtra(ForegroundService.INTENT_SERVICE_DATA, false)}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Обработка соединения с сервисом
    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        // При соединении с сервисом
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            boundService = service as BoundService.ServiceBinder
            isBound = boundService != null
            Log.i("SERVICE", "BOUND SERVICE")
            Log.i("SERVICE", "next fibonacci: ${service.nextFibonacci}")
        }

        // При разрыве соединения с сервисом
        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            val data = binding.editText.text.toString().toInt()

            launch {
                val task = async(Dispatchers.Default) { startCalculations(data) }
                textView.text = task.await()
                mainContainer.addView(TextView(it.context).apply {
                    text = getString(R.string.in_main_thread)
                    textSize = resources.getDimension(R.dimen.main_container_text_size)
                })

            }
        }

        ForegroundService.start(requireContext())
    }

    override fun onStart() {
        super.onStart()
        if (!isBound) {
            val bindServiceIntent = Intent(context, BoundService::class.java)
            activity?.bindService(bindServiceIntent, boundServiceConnection, Context.BIND_AUTO_CREATE)
        }
        activity?.registerReceiver(testReceiver, IntentFilter(ForegroundService.INTENT_ACTION_KEY))
    }

    override fun onStop() {
        activity?.unregisterReceiver(testReceiver)
        if (isBound) {
            activity?.unbindService(boundServiceConnection)
        }
        super.onStop()
    }

    private fun startCalculations(seconds: Int): String {
        val date = Date()
        var diffInSec: Long
        do {
            val currentDate = Date()
            val diffInMs: Long = currentDate.time - date.time
            diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs)
        } while (diffInSec < seconds)

        return diffInSec.toString()
    }

    companion object {
        fun newInstance() = ThreadsFragment()
    }
}