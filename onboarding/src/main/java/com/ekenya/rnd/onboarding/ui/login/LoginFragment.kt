package com.ekenya.rnd.onboarding.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

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


    }

    private fun validateUserInput() {
        binding.loginContinueBtn.setOnClickListener {
            val phoneNumber = binding.loginPhoneNumberInput.editText?.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                binding.loginPhoneNumberInput.error = "Please provide a valid phone number"
            } else if (!phoneNumber.matches(Regex("^\\d{10}$"))) {
                binding.loginPhoneNumberInput.error =
                    "Invalid phone number. Phone number should be 10 digits long."
            } else {
                // Input is valid, navigate to the next fragment
                findNavController().navigate(R.id.loginVerificationFragment)
            }
        }
    }


}