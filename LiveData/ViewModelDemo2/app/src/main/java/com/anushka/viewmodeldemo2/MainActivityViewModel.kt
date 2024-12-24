package com.anushka.viewmodeldemo2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int) : ViewModel() {
	private var total = MutableLiveData<Int>()
	val totalData: MutableLiveData<Int>
		get() = total
	
	init {
		total.value = startingTotal
	}
	
	fun setTotal(input: Int) {
		total.value = total.value?.plus(input)
	}
}
