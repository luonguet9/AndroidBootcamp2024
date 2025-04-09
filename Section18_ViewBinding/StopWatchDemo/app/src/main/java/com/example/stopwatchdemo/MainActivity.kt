package com.example.stopwatchdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stopwatchdemo.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private var isStarted = false
	private lateinit var serviceIntent: Intent
	private var time = 0.0
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		binding.btStart.setOnClickListener {
			startOrStop()
		}
		binding.btReset.setOnClickListener {
			reset()
		}
		serviceIntent = Intent(this, StopWatchService::class.java)
		registerReceiver(
			updateTime,
			IntentFilter(StopWatchService.UPDATED_TIME),
			RECEIVER_NOT_EXPORTED
		)
	}
	
	private fun startOrStop() {
		if (isStarted) {
			stop()
		} else {
			start()
		}
	}
	
	private fun start() {
		serviceIntent.putExtra(StopWatchService.CURRENT_TIME, time)
		startService(serviceIntent)
		binding.btStart.text = "Stop"
		isStarted = true
	}
	
	private fun stop() {
		stopService(serviceIntent)
		binding.btStart.text = "Start"
		isStarted = false
	}
	
	private fun reset() {
		stop()
		time = 0.0
		binding.tvTime.text = getTimeStringFromDouble(time)
	}
	
	private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
		override fun onReceive(p0: Context, p1: Intent) {
			time = p1.getDoubleExtra(StopWatchService.CURRENT_TIME, 0.0)
			binding.tvTime.text = getTimeStringFromDouble(time)
		}
		
	}
	
	private fun getTimeStringFromDouble(time: Double): String {
		val timeInt = time.toInt()
		val hours = timeInt % 86400 / 3600
		val minutes = timeInt % 86400 % 3600 / 60
		val seconds = timeInt % 86400 % 3600 % 60
		return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
	}
}
