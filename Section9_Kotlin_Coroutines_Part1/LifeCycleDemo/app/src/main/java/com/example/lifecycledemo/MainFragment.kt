package com.example.lifecycledemo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.lifecycledemo.R
import com.example.lifecycledemo.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
	private lateinit var binding: FragmentMainBinding
	
	companion object {
		fun newInstance() = MainFragment()
	}
	
	private lateinit var viewModel: MainViewModel
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
		return binding.root
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
		// TODO: Use the ViewModel
		lifecycleScope.launch(Dispatchers.IO) {
			Log.i("MyTag", "Thread name is : ${Thread.currentThread().name}")
		}
	}
	
}
