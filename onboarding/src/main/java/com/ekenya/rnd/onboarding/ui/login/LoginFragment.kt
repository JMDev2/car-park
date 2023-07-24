package com.ekenya.rnd.onboarding.ui.login

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
import com.ekenya.rnd.common.Constants.USER_PIN
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.toast
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.database.UserViewModel
import com.ekenya.rnd.onboarding.databinding.FragmentLoginBinding
import javax.inject.Inject



class LoginFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

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

        //navigateToSignup if you dont have an account
        binding.navigateSignup.setOnClickListener {
            findNavController().navigate(R.id.userDetailsFragment)
        }



    }

    private fun validateUserInput() {
        binding.loginContinueBtn.setOnClickListener {
            val phoneNumber = binding.loginPhoneNumberInput.editText?.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                binding.loginPhoneNumberInput.error = "Please provide a valid phone number"
            } else if (!phoneNumber.matches(Regex("^\\d{10}$"))) {
                binding.loginPhoneNumberInput.error = "Invalid phone number. Phone number should be 10 digits long."
            } else {
                // Input is valid, call the observeUserLogin() function to check for the user
                observeUserLogin(phoneNumber)
            }
        }
    }

    //observe the user phone number
    private fun observeUserLogin(phoneNumber: String) {
        viewModel.getUserByPhoneNumber(phoneNumber).observe(
            viewLifecycleOwner
        ) { user ->
            if (user != null) {
                USER_PIN = user.password
                Log.e("USER","user ${user}")
                findNavController().navigate(R.id.loginVerificationFragment)
            } else {
                toast("No matching phone number")
            }
        }
    }




}