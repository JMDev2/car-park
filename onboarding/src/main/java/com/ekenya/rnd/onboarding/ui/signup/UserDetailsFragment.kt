package com.ekenya.rnd.onboarding.ui.signup

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.User
import com.ekenya.rnd.common.utils.Status
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentUserDetailsBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class UserDetailsFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentUserDetailsBinding


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
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)

        val text =
            "<font color='#505353'>Not yet received the code?</font> <font color='#DE7500'>resend it in 45 sec</font>" +
                    "<font color='#505353'>and</font> <font color='#DE7500'>Policy privacy</font>"
        binding.userTextDesc3.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFieldValidations()
        setupToolbar()
        setupSaveButton()
        setupInputValidation()
        captureUserInput()
        observeRespose()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
    }

    private fun setupSaveButton() {
        val saveButton = binding.userDetailsContinueBtn
        saveButton.visibility = View.VISIBLE // Initially hide the save button

        // Set click listener for the save button
        saveButton.setOnClickListener {
            val user = validateFieldsAndToggleSaveButtonVisibility()
            if (isInputValid()) {
                viewModel.registerUser(user)
                findNavController().navigate(R.id.introFragment)
                showToast("Successs")
            } else {
                toast("Something went wrong.")
            }
        }
    }

    private fun setupInputValidation() {
        val firstNameInput = binding.firstNameInput
        val lastNameInput = binding.secondNameInput
        val phoneNumber = binding.phonNumberInput
        val emailInput = binding.emailInput
        val passwordInput = binding.passwordInput

        // Set focus change listeners for each input field
        firstNameInput.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateFirstName()
            }
        }

        lastNameInput.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateLastName()
            }
        }

        phoneNumber.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validatePhonNumber()
            }
        }

        emailInput.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail()
            }
        }

        passwordInput.editText?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validatePassword()
            }
        }
    }

    private fun validateFirstName() {
        val firstNameInput = binding.firstNameInput
        val firstName = firstNameInput.editText?.text.toString().trim()
        if (firstName.isEmpty()) {
            firstNameInput.error = "First name is required"
        } else {
            firstNameInput.error = null
        }

        validateFieldsAndToggleSaveButtonVisibility()
    }

    private fun validateLastName() {
        val lastNameInput = binding.secondNameInput
        val lastName = lastNameInput.editText?.text.toString().trim()

        if (lastName.isEmpty()) {
            lastNameInput.error = "Last name is required"
        } else {
            lastNameInput.error = null
        }

        validateFieldsAndToggleSaveButtonVisibility()
    }

    //TODO: birthday
    private fun validatePhonNumber() {
        val phonNumberInput = binding.phonNumberInput
        val phonNumber = phonNumberInput.editText?.text.toString().trim()

        if (phonNumber.isEmpty()) {
            phonNumberInput.error = "Last name is required"
        } else {
            phonNumberInput.error = null
        }

        validateFieldsAndToggleSaveButtonVisibility()
    }

    private fun validateEmail() {
        val emailInput = binding.emailInput
        val email = emailInput.editText?.text.toString().trim()

        if (email.isEmpty()) {
            emailInput.error = "Email address is required"
        } else if (!isValidEmail(email)) {
            emailInput.error = "Invalid email address"
        } else {
            emailInput.error = null
        }

        validateFieldsAndToggleSaveButtonVisibility()
    }

    private fun validatePassword() {
        val passwordInput = binding.passwordInput
        val password = passwordInput.editText?.text.toString()

        if (password.isEmpty()) {
            passwordInput.error = "Password is required"
        } else if (password.length < 6) {
            passwordInput.error = "Password must be at least 6 characters long"
        } else {
            passwordInput.error = null
        }

        validateFieldsAndToggleSaveButtonVisibility()
    }

    //checking the user input fields and if valid it displays the button
    private fun validateFieldsAndToggleSaveButtonVisibility(): User {
        val firstName = binding.firstNameInput.editText?.text.toString().trim()
        val lastName = binding.secondNameInput.editText?.text.toString().trim()
        val phoneNumber = binding.phonNumberInput.editText?.text.toString().trim()
        val email = binding.emailInput.editText?.text.toString().trim()
        val password = binding.passwordInput.editText?.text.toString()

        // Log the inputs
        Log.d("ProfileFragment", "First Name: $firstName")
        Log.d("ProfileFragment", "Last Name: $lastName")
        Log.d("ProfileFragment", "Phone Number: $phoneNumber")
        Log.d("ProfileFragment", "Email: $email")
        Log.d("ProfileFragment", "Password: $password")

        val saveButton = binding.userDetailsContinueBtn

        val isValidFirstName = firstName.isNotEmpty()
        val isValidLastName = lastName.isNotEmpty()
        val isValidPhoneNumber = phoneNumber.isNotEmpty()
        val isValidEmail = email.isNotEmpty() && isValidEmail(email)
        val isValidPassword = password.isNotEmpty() && password.length >= 6

        if (isValidFirstName && isValidLastName && isValidPhoneNumber && isValidEmail && isValidPassword) {
            saveButton.isEnabled = true // Enable the button
            saveButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FB8500")) // Set the button's background color to #FB8500
        } else {
            saveButton.isEnabled = false // Disable the button
            saveButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D8DADB")) // Set the button's background color to #D8DADB
        }

        return User(
            email = email,
            firstName = firstName,
            lastName = lastName,
            password = password,
            phoneNumber = phoneNumber
        )
    }



    // Set up field validations and toggle save button visibility
    private fun setupFieldValidations() {
        binding.firstNameInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }

        binding.secondNameInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }

        binding.phonNumberInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }

        binding.emailInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }

        binding.passwordInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }
    }


    private fun isInputValid(): Boolean {
        val firstName = binding.firstNameInput.editText?.text.toString().trim()
        val lastName = binding.secondNameInput.editText?.text.toString().trim()
        val phoneNumber = binding.phonNumberInput.editText?.text.toString().trim()
        val email = binding.emailInput.editText?.text.toString().trim()
        val password = binding.passwordInput.editText?.text.toString()

        return firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty() &&
                isValidEmail(email) && password.isNotEmpty() && password.length >= 6
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex(pattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun captureUserInput(){

    }


    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    private fun observeRespose(){
        viewModel.observeRegisterUserLiveData().observe(
            viewLifecycleOwner
        ){ userresponse ->
            when(userresponse.status){
                Status.SUCCESS -> {

                    //TODO: Dismiss the progress bar
                    val response = userresponse.data
                    response.let {

                    }
                }
                Status.ERROR ->{

                }
                Status.LOADING ->{

                }
            }

        }
    }



}

