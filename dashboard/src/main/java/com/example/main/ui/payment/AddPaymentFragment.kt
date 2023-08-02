package com.example.main.ui.payment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.CardPaymentMode
import com.ekenya.rnd.common.model.MpesaPaymentMode
import com.example.main.R
import com.example.main.adapter.CardPaymentAdapter
import com.example.main.adapter.PaymentModeAdapter
import com.example.main.databinding.FragmentAddPaymentBinding
import javax.inject.Inject


class AddPaymentFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentAddPaymentBinding
    private lateinit var mpesaPaymentModes: List<MpesaPaymentMode>
    private lateinit var cardPaymentModes: List<CardPaymentMode>
    private lateinit var paymentModeAdapter: PaymentModeAdapter
    private lateinit var cardPaymentAdapter: CardPaymentAdapter

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
        binding = FragmentAddPaymentBinding.inflate(layoutInflater, container, false)

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



        val bundle = arguments
        val selectedMpesaPaymentMode = bundle?.getParcelable<MpesaPaymentMode>("paymentMode")
//        val selectCardaPaymentMode = bundle?.getParcelable<CardPaymentMode>("cardPaymentMode")

        selectedMpesaPaymentMode?.let {
            mpesaPaymentModes = listOf(it)
            setupRecyclerView()
            handleEmptyRecyclerView()
        }

        binding.paymentBtn.setOnClickListener {
            findNavController().navigate(R.id.selectPaymentFragment)
        }

    }

    //sets up the recyclerview
    private fun setupRecyclerView() {
        paymentModeAdapter = PaymentModeAdapter(mpesaPaymentModes, viewModel)
        paymentModeAdapter.onItemClick = { paymentMode ->
            Log.d("namba", "${viewModel.phoneInput.value}")
            findNavController().navigate(R.id.dashboardMainFragment)
        }
        binding.addPaymentRecyclerview.adapter = paymentModeAdapter
        binding.addPaymentRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

//    private fun setupRecyclerView1() {
//        cardPaymentAdapter = CardPaymentAdapter(cardPaymentModes, viewModel)
////        paymentModeAdapter'.onItemClick = { paymentMode ->
////            Log.d("namba", "${viewModel.phoneInput.value}")
////            findNavController().navigate(R.id.dashboardMainFragment)
////        }
//        binding.addPaymentRecyclerview.adapter = cardPaymentAdapter
//        binding.addPaymentRecyclerview.layoutManager = LinearLayoutManager(requireContext())
//    }


    //checks if the rcyclerview is empty
    private fun handleEmptyRecyclerView() {
        if (mpesaPaymentModes.isEmpty()) {
            binding.addPaymentRecyclerview.visibility = View.GONE
            binding.constraint.visibility = View.VISIBLE
        } else {
            binding.addPaymentRecyclerview.visibility = View.VISIBLE
            binding.constraint.visibility = View.GONE
        }
    }
}