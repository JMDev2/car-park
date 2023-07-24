package com.example.main.ui.payment

import android.os.Bundle
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
import com.ekenya.rnd.common.model.CardPaymentMode
import com.ekenya.rnd.common.model.MpesaPaymentMode
import com.ekenya.rnd.common.utils.toast
import com.example.main.R
import kotlinx.coroutines.launch
import com.example.main.databinding.FragmentSelectPaymentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


class
SelectPaymentFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentSelectPaymentBinding
    private val selectedMpesaPaymentModes: MutableList<MpesaPaymentMode> = mutableListOf()
    private var selectedMpesaPaymentMode: MpesaPaymentMode? = null



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

        observeUserInput()
        setupRadioGroup()
        updateButtonVisibility()

        binding.proccedToBookMpesaBtn.setOnClickListener {
            val selectedMpesaPaymentMode = MpesaPaymentMode("Mpesa", R.drawable.mpesa, viewModel.phoneInput.toString())
            val bundle = Bundle()
            bundle.putParcelable("paymentMode", selectedMpesaPaymentMode)

            val selectCardPaymentMode = CardPaymentMode("Card", R.drawable.card_img, viewModel.cardInput.toString())
            bundle.putParcelable("cardPaymentMode", selectCardPaymentMode)

            findNavController().navigate(R.id.addPaymentFragment, bundle)
        }



        binding.paymentArrowMpesa.setOnClickListener {
            if (binding.paymentRadioMpesa.isChecked) {
                findNavController().navigate(R.id.mpesaFragment)
            }else{
                toast("Please check the payment method")
            }
        }

        binding.paymentArrowCard.setOnClickListener {
            if (binding.paymentRadioCard.isChecked) {
                findNavController().navigate(R.id.creditCardFragment)
            } else {
                toast("Please check the payment method")
            }
        }

    }



   // checking the radio groups
    private fun setupRadioGroup() {
        val mpesaRadioButton = binding.paymentRadioMpesa
        val cardRadioButton = binding.paymentRadioCard

        mpesaRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cardRadioButton.isChecked = false // Uncheck the Card radio button
                binding.paymentArrowMpesa.isEnabled = true // Enable the arrow button
                selectedMpesaPaymentMode = MpesaPaymentMode("Mpesa",  R.drawable.mpesa, "")
                Log.d("RadioGroup", "Mpesa is checked")
            }
        }

        cardRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mpesaRadioButton.isChecked = false // Uncheck the Mpesa radio button
                binding.paymentArrowMpesa.isEnabled = false // Disable the arrow button
                selectedMpesaPaymentMode = MpesaPaymentMode("Card", R.drawable.mpesa, "")
                Log.d("RadioGroup", "Card is checked")
            }
        }
    }



    private fun observeUserInput() {
        // Create a CoroutineScope using the viewLifecycleOwner's lifecycle
        val coroutineScope = viewLifecycleOwner.lifecycleScope

        // Launch a coroutine within the scope
        coroutineScope.launch(Dispatchers.Main) {
            combine(viewModel.phoneInput, viewModel.cardInput) { userInput, cardInput ->
                userInput to cardInput
            }.collect { (userInput, cardInput) ->
                binding.userPhoneNumberTv.text = userInput
                binding.userCardNumberTv.text = cardInput
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






}

