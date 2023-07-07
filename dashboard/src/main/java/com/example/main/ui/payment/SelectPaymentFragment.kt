package com.example.main.ui.payment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.example.main.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.main.databinding.FragmentBlankBinding
import com.example.main.databinding.FragmentParkingBinding
import com.example.main.databinding.FragmentSelectPaymentBinding
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class
SelectPaymentFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentSelectPaymentBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy{
        ViewModelProvider(requireActivity(),factory)[PaymentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectPaymentBinding.inflate(inflater, container, false)

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

        progressBarr()
        observeUserInput()
        setupRadioGroup()



        binding.proccedToBookMpesaBtn.setOnClickListener {
            findNavController().navigate(R.id.mpesaFragment)
        }

        binding.paymentArrowMpesa.setOnClickListener {
            findNavController().navigate(R.id.mpesaFragment)
        }
    }


    /*
    checking the radio groups

     */
    private fun setupRadioGroup() {
        val mpesaRadioButton = binding.paymentRadioMpesa
        val cardRadioButton = binding.paymentRadioCard

        mpesaRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cardRadioButton.isChecked = false // Uncheck the Card radio button
                Log.d("RadioGroup", "Mpesa is checked")
            }
        }

        cardRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mpesaRadioButton.isChecked = false // Uncheck the Mpesa radio button
                Log.d("RadioGroup", "Card is checked")
            }
        }
    }



    private fun observeUserInput() {
        // Create a CoroutineScope using the viewLifecycleOwner's lifecycle
        val coroutineScope = viewLifecycleOwner.lifecycleScope

        // Launch a coroutine within the created scope
        coroutineScope.launch(Dispatchers.Main) {
            viewModel.userInput.collect { userInput ->
                binding.userPhoneNumberTv.text = userInput
            }
        }
    }



    private fun progressBarr() {
        val totalDuration = 11 * 1000L // Total duration of the timer in milliseconds
        val interval = 100L // Interval between each tick in milliseconds

        val countDownTimer = object : CountDownTimer(totalDuration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((totalDuration - millisUntilFinished) / interval).toInt()
                binding.roundProgressBar.progress = progress
            }

            override fun onFinish() {
                // Timer finished
                binding.roundProgressBar.progress = 0 // Reset the progress bar to the initial state
                start() // Start the timer again to repeat the progress bar

            }
        }

        countDownTimer.start()
    }

}

