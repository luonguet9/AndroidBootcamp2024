package com.example.notificationdemo

import android.app.NotificationManager
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_second)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		receiveInput()
	}
	
	private fun receiveInput() {
		val KEY_REPLY = "key_reply"
		val remoteInput = RemoteInput.getResultsFromIntent(this.intent)
		if (remoteInput != null) {
			val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
			findViewById<TextView>(R.id.tv).text = inputString
			val channelID = "com.example.notificationdemo.channel1"
			val notificationId = 45
			
			val repliedNotification = NotificationCompat.Builder(this, channelID)
				.setSmallIcon(R.drawable.ic_launcher_foreground)
				.setContentText("Your reply received")
				.build()
			val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
			notificationManager.notify(notificationId, repliedNotification)
		}
	}
}
