package com.example.main.ui.profile

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.ImageData
import com.example.main.R
import com.example.main.databinding.FragmentProfileBinding


class ProfileFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentProfileBinding
    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the image URI from the arguments
        val imageUriString = arguments?.getString("imageUri")

        if (imageUriString != null) {
            // Convert the image URI string back to a Uri object
            val imageUri = Uri.parse(imageUriString)
           binding.profileImage.setImageURI(imageUri)
        }

        setupFieldValidations()
        setupToolbar()
        setupSaveButton()
        setupInputValidation()
        showSelectImageDialog()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
    }

    private fun setupSaveButton() {
        val saveButton = binding.profileSaveBtn
        saveButton.visibility = View.VISIBLE // Initially hide the save button

        // Set click listener for the save button
        saveButton.setOnClickListener {
            if (isInputValid()) {
                toast("Input is valid. Saving...")
            } else {
                toast("Please fill all fields correctly.")
            }
        }
    }

    private fun setupInputValidation() {
        val firstNameInput = binding.profileFirstNameInput
        val lastNameInput = binding.profileLastNameInput
        val emailInput = binding.profileEmailInput
        val passwordInput = binding.profilePassowordInput

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
        val firstNameInput = binding.profileFirstNameInput
        val firstName = firstNameInput.editText?.text.toString().trim()

        if (firstName.isEmpty()) {
            firstNameInput.error = "First name is required"
        } else {
            firstNameInput.error = null
        }

        validateFieldsAndToggleSaveButtonVisibility()
    }

    private fun validateLastName() {
        val lastNameInput = binding.profileLastNameInput
        val lastName = lastNameInput.editText?.text.toString().trim()

        if (lastName.isEmpty()) {
            lastNameInput.error = "Last name is required"
        } else {
            lastNameInput.error = null
        }

        validateFieldsAndToggleSaveButtonVisibility()
    }

    private fun validateEmail() {
        val emailInput = binding.profileEmailInput
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
        val passwordInput = binding.profilePassowordInput
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
    private fun validateFieldsAndToggleSaveButtonVisibility() {
        val firstName = binding.profileFirstNameInput.editText?.text.toString().trim()
        val lastName = binding.profileLastNameInput.editText?.text.toString().trim()
        val email = binding.profileEmailInput.editText?.text.toString().trim()
        val password = binding.profilePassowordInput.editText?.text.toString()

        // Log the inputs
        Log.d("ProfileFragment", "First Name: $firstName")
        Log.d("ProfileFragment", "Last Name: $lastName")
        Log.d("ProfileFragment", "Email: $email")
        Log.d("ProfileFragment", "Password: $password")

        val saveButton = binding.profileSaveBtn

        val isValidFirstName = firstName.isNotEmpty()
        val isValidLastName = lastName.isNotEmpty()
        val isValidEmail = email.isNotEmpty() && isValidEmail(email)
        val isValidPassword = password.isNotEmpty() && password.length >= 6

        if (isValidFirstName && isValidLastName && isValidEmail && isValidPassword) {
            saveButton.isEnabled = true // Enable the button
            saveButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FB8500")) // Set the button's background color to #FB8500
        } else {
            saveButton.isEnabled = false // Disable the button
            saveButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D8DADB")) // Set the button's background color to #D8DADB
        }
    }


    // Set up field validations and toggle save button visibility
    private fun setupFieldValidations() {
        binding.profileFirstNameInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }

        binding.profileLastNameInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }

        binding.profileEmailInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }

        binding.profilePassowordInput.editText?.doOnTextChanged { _, _, _, _ ->
            validateFieldsAndToggleSaveButtonVisibility()
        }
    }




    private fun isInputValid(): Boolean {
        val firstName = binding.profileFirstNameInput.editText?.text.toString().trim()
        val lastName = binding.profileLastNameInput.editText?.text.toString().trim()
        val email = binding.profileEmailInput.editText?.text.toString().trim()
        val password = binding.profilePassowordInput.editText?.text.toString()

        return firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() &&
                isValidEmail(email) && password.isNotEmpty() && password.length >= 6
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex(pattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }



    private fun showSelectImageDialog() {
        binding.chageProfilePictureTv.setOnClickListener {
            val openCameraFragment = OpenCameraFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, openCameraFragment)
            transaction.commit()
        }
    }
}

