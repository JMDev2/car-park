package com.ekenya.rnd.onboarding.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentSignUpBinding
import javax.inject.Inject


class SignUpFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentSignUpBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, factory)[SignUpViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validateUserInput()




    }

    private fun validateUserInput() {
        binding.signupContinueBtn.setOnClickListener {
            val phoneNumber = binding.signupPhoneNumberInput.editText?.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                binding.signupPhoneNumberInput.error = "Please provide a valid phone number"
            } else if (!phoneNumber.matches(Regex("^\\d{10}$"))) {
                binding.signupPhoneNumberInput.error =
                    "Invalid phone number. Phone number should be 10 digits long."
            } else {
                // Input is valid, navigate to the next fragment
                findNavController().navigate(R.id.userDetailsFragment)
            }
        }
    }






}