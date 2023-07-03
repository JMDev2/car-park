package com.example.main.ui.booking

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.toast
import com.example.main.R
import com.example.main.databinding.FragmentBookingBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
class BookingFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentBookingBinding
    private var isDatePickerOpen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingBinding.inflate(inflater, container, false)


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

        // Set the toolbar as the support action bar





        binding.clickOverlay.setOnClickListener {
            toggleDatePicker()
        }
        binding.clickTimeFromOverlay.setOnClickListener {
            openTimeFromPicker()
        }
        binding.clickTimeToOverlay.setOnClickListener {
                openTimeToPicker()
        }
        binding.proccedToPayBtn.setOnClickListener {
            validateDateTimeInputs()
        }
        binding.proccedToPayBtn.setOnClickListener {
            findNavController().navigate(R.id.selectPaymentFragment)
        }

        // Rest of your code...
    }

    private fun toggleDatePicker() {
        if (isDatePickerOpen) {
            // Close the date picker
            isDatePickerOpen = false
            // Optionally, you can perform any other necessary actions when the date picker is closed
        } else {
            // Open the date picker
            isDatePickerOpen = true
            showDatePicker()

        }
    }

    //date picker
    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selection

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(calendar.time)

            binding.bookDateInput.editText?.text = SpannableStringBuilder("Date: $formattedDate")

        }

        datePicker.show(childFragmentManager, datePicker.toString())
    }


    //time from picker
    private fun openTimeFromPicker() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
            val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            binding.bookTimeFromInput.editText?.setText(selectedTime)
        }, currentHour, currentMinute, true)

        timePickerDialog.show()
    }

    //time to picker
   private fun openTimeToPicker(){
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
            val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            binding.bookTimeToInput.editText?.setText(selectedTime)
        }, currentHour, currentMinute, true)

        timePickerDialog.show()
   }


//l3tm31n@h0m3





    //TODo: to remove this code
    //text outline
//    private fun textOutline() {
//        binding.bookDateInput.editText?.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//
//            override fun afterTextChanged(s: Editable?) {
//                val date = s?.toString()
//                if (!date.isNullOrEmpty()) {
//                    binding.bookDateInput.editText?.hint = "Date: $date"
//                } else {
//                    binding.bookDateInput.editText?.hint = "Date"
//                }
//            }
//        })
//    }

    //validating date, time inputs

    private fun validateDateTimeInputs(){
        val date = binding.bookDateInput.editText?.text.toString().trim()
        val timeFrom = binding.bookTimeFromInput.editText?.text.toString().trim()
        val timeTo = binding.bookTimeToInput.editText?.text.toString().trim()


        if (date.isEmpty()) {
            binding.bookDateInput.error = "User name is required"
        }
        if (timeFrom.isEmpty()) {
            binding.bookTimeFromInput.error = "User name is required"
        }
        if (timeTo.isEmpty()) {
            binding.bookTimeToInput.error = "User name is required"
        }

    }





}