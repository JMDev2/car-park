package com.example.main.ui.payment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.PaymentMode
import com.ekenya.rnd.common.utils.toast
import com.example.main.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.main.databinding.FragmentSelectPaymentBinding
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class
SelectPaymentFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentSelectPaymentBinding
    private val selectedPaymentModes: MutableList<PaymentMode> = mutableListOf()
    private var selectedPaymentMode: PaymentMode? = null



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
        updateButtonVisibility()

        binding.proccedToBookMpesaBtn.setOnClickListener {

            val selectedPaymentMode = PaymentMode("Mpesa", R.drawable.mpesa, viewModel.userInput.toString())
            val bundle = Bundle()
            bundle.putParcelable("paymentMode", selectedPaymentMode)

            findNavController().navigate(R.id.addPaymentFragment, bundle)
        }



        binding.paymentArrowMpesa.setOnClickListener {
            if (binding.paymentRadioMpesa.isChecked) {
                findNavController().navigate(R.id.mpesaFragment)
            }else{
                toast("Please check the payment method")
            }
        }

        binding.paymentRadioCard.setOnClickListener {
            toast("The method selected is not available at the moment")
            binding.paymentArrowMpesa.isEnabled = false // Disable the arrow button
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
                binding.paymentArrowMpesa.isEnabled = true // Enable the arrow button
                selectedPaymentMode = PaymentMode("Mpesa",  R.drawable.mpesa, "")
                Log.d("RadioGroup", "Mpesa is checked")
            }
        }

        cardRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mpesaRadioButton.isChecked = false // Uncheck the Mpesa radio button
                binding.paymentArrowMpesa.isEnabled = false // Disable the arrow button
                selectedPaymentMode = PaymentMode("Card", R.drawable.mpesa, "")
                Log.d("RadioGroup", "Card is checked")
            }
        }
    }

//    private fun setupRadioGroup() {
//        val mpesaRadioButton = binding.paymentRadioMpesa
//        val cardRadioButton = binding.paymentRadioCard
//
//        mpesaRadioButton.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                cardRadioButton.isChecked = false // Uncheck the Card radio button
//                binding.paymentArrowMpesa.isEnabled = true // Enable the arrow button
//                Log.d("RadioGroup", "Mpesa is checked")
//            }
//        }
//
//        cardRadioButton.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                mpesaRadioButton.isChecked = false // Uncheck the Mpesa radio button
//                binding.paymentArrowMpesa.isEnabled = false // Disable the arrow button
//                Log.d("RadioGroup", "Card is checked")
//            }
//        }
//    }

    private fun observeUserInput() {
        // Create a CoroutineScope using the viewLifecycleOwner's lifecycle
        val coroutineScope = viewLifecycleOwner.lifecycleScope

        // Launch a coroutine within the created scope
        coroutineScope.launch(Dispatchers.Main) {
            viewModel.userInput.collect { userInput ->
                binding.userPhoneNumberTv.text = userInput
                updateButtonVisibility()
            }
        }
    }

    private fun updateButtonVisibility() {
        if (binding.userPhoneNumberTv.text.isNullOrEmpty()) {
            binding.proccedToBookMpesaBtn.visibility = View.GONE // Hide the button
        } else {
            binding.proccedToBookMpesaBtn.visibility = View.VISIBLE // Show the button
        }
    }





    //TODO: to be removed
    private fun progressBarr() {
        val totalDuration = 11 * 1000L // Total duration of the timer in milliseconds
        val interval = 100L // Interval between each tick in milliseconds

        val countDownTimer = object : CountDownTimer(totalDuration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((totalDuration - millisUntilFinished) / interval).toInt()
               // binding.roundProgressBar.progress = progress
            }

            override fun onFinish() {
                // Timer finished
              //  binding.roundProgressBar.progress = 0 // Reset the progress bar to the initial state
                start() // Start the timer again to repeat the progress bar

            }
        }

        countDownTimer.start()
    }

}

