package com.example.recyclerviewdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
	private val fruitsList = listOf(
		Fruit("Mango", "Joe"),
		Fruit("Apple", "Frank"),
		Fruit("Banana", "Tom"),
		Fruit("Guava", "Joe"),
		Fruit("Lemon", "Alex"),
		Fruit("Pear", "Joe"),
		Fruit("Orange", "Alex")
	)
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		val recyclerView = findViewById<RecyclerView>(R.id.rv)
		recyclerView.layoutManager = LinearLayoutManager(this)
		recyclerView.adapter = MyRecyclerViewAdapter(fruitsList) { selectedItem: Fruit ->
			listItemClicked(selectedItem)
		}
	}
	
	private fun listItemClicked(fruit: Fruit) {
		Toast.makeText(
			this@MainActivity,
			"Supplier name is : ${fruit.supplier} and Fruit is : ${fruit.name}",
			Toast.LENGTH_LONG
		).show()
	}
}
