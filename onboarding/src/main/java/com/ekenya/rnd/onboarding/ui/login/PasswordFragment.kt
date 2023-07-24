package com.ekenya.rnd.onboarding.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.Constants.USER_PIN
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.toast
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentPasswordBinding
import javax.inject.Inject


class PasswordFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentPasswordBinding

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
        binding = FragmentPasswordBinding.inflate(layoutInflater, container, false)


        val text = "<font color='#505353'>Forgot your Password?</font> <font color='#DE7500'>Resend it</font>"
        binding.loginPasswordDesc.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)


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
        binding.loginPasswordContinueBtn.setOnClickListener {
            val password = binding.loginPasswordInput.editText?.text.toString().trim()

            if (password.isEmpty()) {
                binding.loginPasswordInput.error = "Please provide a password"
            } else if (password.length < 6) {
                binding.loginPasswordInput.error = "Password must be at least 6 characters long"
            } else {
                // Input is valid, navigate to the next fragment
                //observeUserPassword(password)
                val storedPassword = USER_PIN
                val enteredPassword = password

                if (enteredPassword == storedPassword) {
                    toast("Correct password")
                } else {
                    toast("Incorrect password")
                }

            }
        }
    }

    private fun observeUserPassword(password: String) {
        viewModel.getUserByPhoneNumber(password).observe(viewLifecycleOwner) { user ->
            if (user != null) {
                val storedPassword = user.password
                val enteredPassword = password

                if (enteredPassword == storedPassword) {
                    toast("Correct password")
                } else {
                    toast("Incorrect password")
                }
            } else {
                toast("No matching phone number")
            }
        }

    }





}