package com.example.main.ui.payment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.example.main.R
import com.example.main.databinding.FragmentMpesaBinding
import javax.inject.Inject


class MpesaFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentMpesaBinding
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy{
        ViewModelProvider(requireActivity(),factory)[PaymentViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMpesaBinding.inflate(inflater, container, false)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        // Enable the back arrow in the toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Set the click listener for the back arrow
        binding.toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validateUserInput()
        switchToggleToSavePhoneNumber()
    }



    /*
    saves the user input on switch toggle and sets its value in the viewmodel
     */
    private fun switchToggleToSavePhoneNumber() {
        binding.toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Save the user input
                val phoneInput = binding.mpesaPhoneNumberInput.editText?.text.toString().trim()
                if (phoneInput.isEmpty() || phoneInput.matches(Regex("^\\d{9}$")).not()) {
                    binding.mpesaPhoneNumberInput.error = "Provide a valid phone number"
                    binding.toggleSwitch.isChecked = false // Set isChecked to false
                } else {
                    val formattedInput = "+254$phoneInput" // Prepend "+254" to the user input
                    viewModel.setUserInput(formattedInput) //setting the input in the viewmodel
                   // binding.mesaPhoneNumberInput.tag = userInput
                    Log.d("MpesaFragment", "User Input: $phoneInput")
                }
            } else {
                // Unsave the user input
                binding.mpesaPhoneNumberInput.editText?.setText(binding.mpesaPhoneNumberInput.tag as? String)
            }
        }
    }



    private fun validateUserInput() {
        binding.confirmMpesaPhoneNumber.setOnClickListener {
            val phoneNumber = binding.mpesaPhoneNumberInput.editText?.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                binding.mpesaPhoneNumberInput.error = "Please provide a valid phone number"
            } else if (!phoneNumber.matches(Regex("^\\d{9}$"))) {
                binding.mpesaPhoneNumberInput.error =
                    "Invalid phone number. Phone number should be 10 digits long."
            } else {
                // Input is valid, navigate to the next fragment
                val formattedPhoneNumber = "+254$phoneNumber" // Prepend "+254" to the phone number
                Log.d("MpesaFragment", "Phone Number: $formattedPhoneNumber")
                findNavController().navigate(R.id.selectPaymentFragment)
            }
        }


    }
}