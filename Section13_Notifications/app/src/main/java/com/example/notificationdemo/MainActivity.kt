package com.example.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
	private val channelID = "com.example.notificationdemo.channel1"
	private var notificationManager: NotificationManager? = null
	private val KEY_REPLY = "key_reply"
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
		createNotificationChannel(channelID, "DemoChannel", "this is a demo")
		val btSendNotification = findViewById<Button>(R.id.bt_send_notification)
		btSendNotification.setOnClickListener {
			displayNotification()
		}
	}
	
	private fun displayNotification() {
		val notificationId = 45
		val tapResultIntent = Intent(this, SecondActivity::class.java)
		val pendingIntent =
			PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_MUTABLE)
		
		//action button 1
		val detailsActivityIntent = Intent(this, DetailsActivity::class.java)
		val actionIntent1 =
			PendingIntent.getActivity(this, 0, detailsActivityIntent, PendingIntent.FLAG_IMMUTABLE)
		val action1 = NotificationCompat.Action.Builder(0, "Details", actionIntent1).build()
		
		//action button 2
		val settingsActivityIntent = Intent(this, SettingsActivity::class.java)
		val actionIntent2 =
			PendingIntent.getActivity(this, 0, settingsActivityIntent, PendingIntent.FLAG_IMMUTABLE)
		val action2 = NotificationCompat.Action.Builder(0, "Settings", actionIntent2).build()
		
		//reply action
		val remoteInput = RemoteInput.Builder(KEY_REPLY).run {
			setLabel("Insert your name here")
			build()
		}
		val replyAction =
			NotificationCompat.Action.Builder(0, "REPLY", pendingIntent)
				.addRemoteInput(remoteInput)
				.build()
		
		val notification = NotificationCompat.Builder(this, channelID)
			.setContentTitle("Demo Title")
			.setContentText("This is a demo notification")
			.setSmallIcon(R.drawable.ic_launcher_background)
			.setAutoCancel(true)
			.setPriority(NotificationCompat.PRIORITY_HIGH)
//			.setContentIntent(pendingIntent)
			.addAction(action1)
			.addAction(action2)
			.addAction(replyAction)
			.build()
		notificationManager?.notify(notificationId, notification)
	}
	
	private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val importance = NotificationManager.IMPORTANCE_HIGH
			val channel = NotificationChannel(id, name, importance).apply {
				description = channelDescription
			}
			notificationManager?.createNotificationChannel(channel)
		}
	}
}
