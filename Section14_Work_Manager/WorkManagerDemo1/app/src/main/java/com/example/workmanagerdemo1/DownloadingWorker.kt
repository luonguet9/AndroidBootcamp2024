package com.example.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Date

class DownloadingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
	override fun doWork(): Result {
		try {
			for (i in 0..3000) {
				Log.i("MY_TAG", "Downloading: $i")
			}
			val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
			val currentDate = time.format(Date())
			Log.i("MY_TAG", "Completed: $currentDate")
			return Result.success()
		} catch (e: Exception) {
			return Result.failure()
		}
	}
	
}
