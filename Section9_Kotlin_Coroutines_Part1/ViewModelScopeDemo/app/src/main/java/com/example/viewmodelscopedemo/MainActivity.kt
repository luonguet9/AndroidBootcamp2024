package com.example.viewmodelscopedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.getUserData()
        mainActivityViewModel.users.observe(this) { myUsers ->
            myUsers.forEach {
                Log.i("MyTag", "name is ${it.name}")
            }
        }
    }
}
