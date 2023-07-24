package com.ekenya.rnd.onboarding.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.toast
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentLoginVerificationBinding

class LoginVerificationFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentLoginVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentLoginVerificationBinding.inflate(layoutInflater, container, false)

        val text = "<font color='#505353'>Not yet received code?</font> <font color='#DE7500'>Resend it in 45 sec</font>"
        binding.verificationTextDesc.text =
            HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)

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
        binding.verificationCodeContinueBtn.setOnClickListener {
            val verificationCode = binding.verificationCodeInput.editText?.text.toString().trim()
            Log.d("VerificationCode", "Code: $verificationCode")

            if (verificationCode.isEmpty()) {
                binding.verificationCodeInput.error = "Please provide a verification code"
            } else if (!verificationCode.matches(Regex("^\\d{3}$"))) {
                binding.verificationCodeInput.error = "Invalid verification code. Code should only contain digits."
            } else {
                // Input is valid, navigate to the next fragment if the code is "1000"
                if (verificationCode == "100") {
                    findNavController().navigate(R.id.passwordFragment)
                } else {
                    toast("Please insert the correct code")
                }
            }
        }
    }



}