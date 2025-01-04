package com.anushka.activitylifecycledemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MY_TAG", "onCreate: $this")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val greetingTextView = findViewById<TextView>(R.id.tvHello)
        val inputField = findViewById<EditText>(R.id.etName)
        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val offersButton = findViewById<Button>(R.id.btnOffers)
        var enteredName = ""
        submitButton.setOnClickListener {
            enteredName = inputField.text.toString()
            if (enteredName == ""){
                offersButton.visibility = View.INVISIBLE
                greetingTextView.text = ""
                Toast.makeText(
                    this@MainActivity,
                    "Please, enter your name!",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                val message = "Welcome $enteredName"
                greetingTextView.text = message
                inputField.text.clear()
                offersButton.visibility = View.VISIBLE
            }
        }
        offersButton.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("USER",enteredName)
            startActivity(intent)
        }
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
