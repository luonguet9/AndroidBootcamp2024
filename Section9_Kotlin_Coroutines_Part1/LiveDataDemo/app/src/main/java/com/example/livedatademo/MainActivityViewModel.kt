package com.example.livedatademo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.livedatademo.model.User
import com.example.livedatademo.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {
	
	private var usersRepository = UserRepository()
	var users = liveData(Dispatchers.IO) {
		val result = usersRepository.getUsers()
		emit(result)
	}
	/*var users: MutableLiveData<List<User>?> = MutableLiveData()

	fun getUsers() {
		viewModelScope.launch {
			var result: List<User>? = null
			withContext(Dispatchers.IO) {
				result = usersRepository.getUsers()
			}
			users.value = result
		}
	}*/
	
}
