package com.ekenya.rnd.onboarding.ui.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.toast
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentSignUpVerificationBinding


class SignUpVerificationFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentSignUpVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpVerificationBinding.inflate(layoutInflater, container, false)

        val text =
            "<font color='#505353'>Not yet received the code?</font> <font color='#DE7500'>resend it in 45 sec</font>" +
                    "<font color='#505353'>and</font> <font color='#DE7500'>Policy privacy</font>"
        binding.signupVerificationTextDesc.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)

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
        binding.signupVerificationContinueBtn.setOnClickListener {
            val verificationCode = binding.signupVerificationInput.editText?.text.toString().trim()
            Log.d("VerificationCode", "Code: $verificationCode")

            if (verificationCode.isEmpty()) {
                binding.signupVerificationInput.error = "Please provide a verification code"
            } else if (!verificationCode.matches(Regex("^\\d{3}$"))) {
                binding.signupVerificationInput.error = "Invalid verification code. Code should only contain digits."
            } else {
                // Input is valid, navigate to the next fragment if the code is "1000"
                if (verificationCode == "1000") {
                    findNavController().navigate(R.id.passwordFragment)
                } else {
                    toast("Please insert the correct code")
                }
            }
        }
    }


}

