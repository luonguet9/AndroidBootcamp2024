package com.anushka.viewmodeldemo1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
	private val count = MutableLiveData(0)
	val countData: MutableLiveData<Int>
		get() = count
	
	fun updatedCount() {
		count.value = count.value?.plus(1)
	}
	
}
