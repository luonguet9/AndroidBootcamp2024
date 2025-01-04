package com.anushka.activitylifecycledemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MY_TAG", "onCreate: $this")
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userName = intent.getStringExtra("USER")
        val textView = findViewById<TextView>(R.id.tvOffer)
        val message = "$userName ,you will get  free access to all the content for one month "
        textView.text = message
    }
    
    override fun onStart() {
        super.onStart()
        Log.i("MY_TAG", "onStart: $this")
    }
    
    override fun onResume() {
        super.onResume()
        Log.i("MY_TAG", "onResume: $this")
    }
    
    override fun onPause() {
        super.onPause()
        Log.i("MY_TAG", "onPause: $this")
    }
    
    override fun onStop() {
        super.onStop()
        Log.i("MY_TAG", "onStop: $this")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.i("MY_TAG", "onDestroy: $this")
    }
    
    override fun onRestart() {
        super.onRestart()
        Log.i("MY_TAG", "onRestart: $this")
    }
}
