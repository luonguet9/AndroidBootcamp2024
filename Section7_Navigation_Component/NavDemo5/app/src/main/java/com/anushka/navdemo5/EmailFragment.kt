package com.anushka.navdemo5


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.anushka.navdemo5.databinding.FragmentEmailBinding


/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {
	
	private lateinit var binding: FragmentEmailBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
		val userName = arguments?.getString("USER_NAME")
		binding.submitButton.setOnClickListener {
			if (binding.emailEditText.text.toString().isNotEmpty()) {
				val bundle = bundleOf(
					"USER_EMAIL" to binding.emailEditText.text.toString(),
					"USER_NAME" to userName
				)
				findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)
			} else {
				Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_LONG)
					.show()
			}
		}
		return binding.root
	}
}
