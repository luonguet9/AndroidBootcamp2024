package com.anushka.didemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
	@Inject
	lateinit var smartPhone: SmartPhone
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		/*val smartPhone = SmartPhone(Battery(), SIMCard(ServiceProvider()), MemoryCard())
		smartPhone.makeACallWithRecording()*/
		
		/*DaggerSmartPhoneComponent.create()
			.inject(this)
		smartPhone.makeACallWithRecording()*/
		
		(application as SmartPhoneApplication).smartPhoneComponent.inject(this)
		smartPhone.makeACallWithRecording()
	}
}
