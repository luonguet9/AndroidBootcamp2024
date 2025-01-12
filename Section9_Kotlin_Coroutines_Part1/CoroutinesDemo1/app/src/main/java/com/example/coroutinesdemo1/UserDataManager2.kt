package com.example.coroutinesdemo1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager2 {
	// Structured concurrency:
	// Guarantees to complete all the works started within the child scope
	// before the return of the suspend function
	private var count = 0
	private lateinit var deferred: Deferred<Int>
	suspend fun getTotalUserCount(): Int {
		coroutineScope {
			launch(Dispatchers.IO) {
				delay(1000)
				count = 50
			}
			deferred = async(Dispatchers.IO) {
				delay(3000)
				return@async 70
			}
		}
		return count + deferred.await()
	}
}
