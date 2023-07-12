package com.example.main.ui.booking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.example.main.R
import com.example.main.databinding.FragmentBookingBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

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
        val receivedItem = arguments?.getParcelable<ParkingResponseItem>("item")
        receivedItem?.let { parkingItem ->
            binding.bookTitleTv.text = parkingItem.title
            binding.bookLocationTv.text = parkingItem.location
            binding.bookAmountTv.text = parkingItem.description

            Picasso.get().load(parkingItem.image).into(binding.bookParkingImage)
        }



        validateDateTimeInputs()



        binding.clickOverlay.setOnClickListener {
            toggleDatePicker()
        }
        binding.clickTimeFromOverlay.setOnClickListener {
            openTimeFromPicker()
        }
        binding.clickTimeToOverlay.setOnClickListener {
                openTimeToPicker()
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
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, day ->
            calendar.set(year, month, day)

            val selectedTimeInMillis = calendar.timeInMillis
            val currentTimeInMillis = System.currentTimeMillis()
            val minTimeInMillis = currentTimeInMillis - TimeUnit.DAYS.toMillis(1) // 24 hours ago
            val maxTimeInMillis = currentTimeInMillis + TimeUnit.DAYS.toMillis(1) // 24 hours from now

            /*
            check the selected date
             */
            if (selectedTimeInMillis < minTimeInMillis || selectedTimeInMillis > maxTimeInMillis) {
                Toast.makeText(requireContext(), "Please select a valid date", Toast.LENGTH_SHORT).show()
            } else {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(calendar.time)

                binding.bookDateInput.editText?.text = SpannableStringBuilder("Date: $formattedDate")
                Log.d("BookingFragment", "date: ${formattedDate}")
            }
        }, currentYear, currentMonth, currentDay)

        datePickerDialog.datePicker.minDate = calendar.timeInMillis - TimeUnit.DAYS.toMillis(1) // 24 hours ago
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis + TimeUnit.DAYS.toMillis(1) // 24 hours from now

        datePickerDialog.show()
    }




    //time from picker
    private fun openTimeFromPicker() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val minHour = currentHour // Minimum allowed hour
        val maxHour = 23 // Maximum allowed hour

        val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
            if (hourOfDay < minHour || hourOfDay > maxHour) {
                Toast.makeText(requireContext(), "Please select a valid time", Toast.LENGTH_SHORT).show()
            } else {
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                binding.bookTimeFromInput.editText?.setText(selectedTime)
                Log.d("BookingFragment", "time from: $selectedTime")
            }
        }, currentHour, currentMinute, true)

        timePickerDialog.show()
    }




    //time to picker
    private fun openTimeToPicker() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val minHour = (currentHour + 2) % 24 // Minimum allowed hour (current hour + 2, with wrapping around 24-hour format)
        val maxHour = 23 // Maximum allowed hour

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                if (hourOfDay < minHour || hourOfDay > maxHour) {
                    Toast.makeText(requireContext(), "Selected time should not be less than 2 hours", Toast.LENGTH_SHORT).show()
                } else {
                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    binding.bookTimeToInput.editText?.setText(selectedTime)
                    Log.d("BookingFragment", "Time to: $selectedTime")
                }
            },
            currentHour,
            currentMinute,
            true // Set is24HourView to true for 24-hour format
        )

        timePickerDialog.updateTime(currentHour, currentMinute) // Set the initial time to the current hour and minute
        timePickerDialog.show()
    }





//l3tm31n@h0m3


    //validating date, time inputs

    private fun validateDateTimeInputs() {
        binding.proccedToPayBtn.setOnClickListener {
            val date = binding.bookDateInput.editText?.text.toString().trim()
            val timeFrom = binding.bookTimeFromInput.editText?.text.toString().trim()
            val timeTo = binding.bookTimeToInput.editText?.text.toString().trim()

            if (date.isEmpty()) {
                binding.bookDateInput.error = "Date is required"
            } else if (timeFrom.isEmpty()) {
                binding.bookTimeFromInput.error = "Starting time is required"
            } else if (timeTo.isEmpty()) {
                binding.bookTimeToInput.error = "Ending time is required"
            } else {
                // All inputs are validated, save and move to the next fragment
               // saveDateTimeInputs(date, timeFrom, timeTo)
                moveToNextFragment()
            }
        }
    }

//    private fun saveDateTimeInputs(date: String, timeFrom: String, timeTo: String) {
//        // Save the input values in your ViewModel or any other appropriate storage mechanism
//        viewModel.saveDateTimeInputs(date, timeFrom, timeTo)
//    }

    private fun moveToNextFragment() {
        // Move to the next fragment using Navigation Component or any other navigation mechanism
        findNavController().navigate(R.id.selectPaymentFragment)
    }






}