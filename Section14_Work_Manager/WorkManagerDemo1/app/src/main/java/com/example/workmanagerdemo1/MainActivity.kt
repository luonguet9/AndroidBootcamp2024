package com.example.workmanagerdemo1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		findViewById<Button>(R.id.bt_start).setOnClickListener {
			setPeriodicWorkRequest()
		}
	}
	
	private fun setOneTimeWorkRequest() {
		val workerManger = WorkManager.getInstance(applicationContext)
		val constraints = Constraints.Builder()
			.setRequiresCharging(true)
			.setRequiredNetworkType(NetworkType.CONNECTED)
			.build()
		val data = Data.Builder()
			.putInt(KEY_COUNT_VALUE, 125)
			.build()
		val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
			.setConstraints(constraints)
			.setInputData(data)
			.build()
		val filterRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
			.build()
		val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
			.build()
		val downloadingWorker = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
			.build()
		
		val parallelWorks = mutableListOf<OneTimeWorkRequest>()
		parallelWorks.add(downloadingWorker)
		parallelWorks.add(filterRequest)
		
		workerManger.beginWith(parallelWorks)
			.then(compressingRequest)
			.then(uploadRequest)
			.enqueue()
		workerManger.getWorkInfoByIdLiveData(uploadRequest.id).observe(this) {
			findViewById<TextView>(R.id.tv).text = it.state.name
			if (it.state.isFinished) {
				val data = it.outputData
				val message = data.getString(UploadWorker.KEY_WORKER)
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
			}
		}
	}
	
	private fun setPeriodicWorkRequest() {
		val periodicWorkRequest =
			PeriodicWorkRequest.Builder(DownloadingWorker::class.java, 16, TimeUnit.MINUTES)
				.build()
		WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
	}
	
	companion object {
		const val KEY_COUNT_VALUE = "key_count"
	}
}
