package com.ekenya.rnd.onboarding.ui.signup

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.User
import com.ekenya.rnd.common.utils.Status
import com.ekenya.rnd.common.utils.toast
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentLoginBinding
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
        eyeToggle()
        saveUser()



//        binding.signupButton.setOnClickListener {
//            registerUser()
//            //findNavController().navigate(R.id.loginFragment)
//        }


    }


    private fun registerUser(): User? {
        val name = binding.signupUsername.text.toString().trim()
        val full_name = binding.signupFullName.text.toString().trim()
        val email = binding.signupEmail.text.toString().trim()
        val password = binding.signupPassword.text.toString().trim()
        val confirmPassword = binding.signupConfirmPassword.text.toString().trim()

        if (name.isEmpty()) {
            binding.signupUsername.error = "User name is required"
            binding.signupUsername.requestFocus()
            return null
        }

        if (full_name.isEmpty()) {
            binding.signupFullName.error = "Full name is required"
            binding.signupFullName.requestFocus()
            return null
        }

        if (email.isEmpty()) {
            binding.signupEmail.error = "Email is required"
            binding.signupEmail.requestFocus()
            return null
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.signupEmail.error = "Please provide a valid email address"
            binding.signupEmail.requestFocus()
            return null
        }

        if (password.isEmpty()) {
            binding.signupPassword.error = "Password is required"
            binding.signupPassword.requestFocus()
            return null
        }

        if (password.length < 6) {
            binding.signupPassword.error = "Minimum password length should be 6 characters"
            binding.signupPassword.requestFocus()
            return null
        }

        if (confirmPassword != password) {
            binding.signupConfirmPassword.error = "Passwords do not match"
            binding.signupConfirmPassword.requestFocus()
            return null
        }

        val user = User(
            email = email,
            name = name,
            password = password,
            username = full_name
        )

        // Call a function to handle the user registration with the `user` object

        return user
    }

// Usage:
    private fun saveUser() {
    binding.signupButton.setOnClickListener {
        val user = registerUser()
        if (isUserValid(user)) {
            if (user != null) {
                // Save the `user` object or perform any necessary operations with it
                viewModel.registerUser(user)
            }
            findNavController().navigate(R.id.loginFragment)

            Log.d("signup", "user $user")
        } else {
            toast("Please fill all the fields")

        }
    }
}

    private fun isUserValid(user: User?): Boolean{
        return user != null
    }

    private fun eyeToggle() {
        var isPasswordVisible = false
        binding.signupImageToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Show password
                binding.signupPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.signupImageToggle.setImageResource(com.ekenya.rnd.common.R.drawable.baseline_visibility_off_24)
            } else {
                // Hide password
                binding.signupPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.signupImageToggle.setImageResource(com.ekenya.rnd.common.R.drawable.baseline_visibility_24)
            }

            // Move the cursor to the end of the text
            binding.signupPassword.setSelection(binding.signupPassword.text.length)
        }

        binding.signupImageToggle1.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Show password
                binding.signupConfirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.signupImageToggle1.setImageResource(com.ekenya.rnd.common.R.drawable.baseline_visibility_off_24)
            } else {
                // Hide password
                binding.signupConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.signupImageToggle1.setImageResource(com.ekenya.rnd.common.R.drawable.baseline_visibility_24)
            }

            // Move the cursor to the end of the text
            binding.signupConfirmPassword.setSelection(binding.signupConfirmPassword.text.length)
        }
    }



}