package com.example.viewmodelscopedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodelscopedemo.model.User
import com.example.viewmodelscopedemo.model.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivityViewModel : ViewModel() {
	private var userRepository = UserRepository()
	var users: MutableLiveData<List<User>?> = MutableLiveData()
	
	fun getUserData() {
		viewModelScope.launch {
			var result: List<User>?
			withContext(Dispatchers.IO) {
				result = userRepository.getUsers()
			}
			users.value = result
		}
		
	}
}
