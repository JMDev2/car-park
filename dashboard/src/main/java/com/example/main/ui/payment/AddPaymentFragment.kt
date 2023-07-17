package com.example.main.ui.payment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.PaymentMode
import com.example.main.R
import com.example.main.adapter.PaymentModeAdapter
import com.example.main.databinding.FragmentAddPaymentBinding
import javax.inject.Inject


class AddPaymentFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentAddPaymentBinding
    private lateinit var paymentModes: List<PaymentMode>
    private lateinit var paymentModeAdapter: PaymentModeAdapter

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
        val selectedPaymentMode = bundle?.getParcelable<PaymentMode>("paymentMode")

        selectedPaymentMode?.let {
            paymentModes = listOf(it)
            setupRecyclerView()
            handleEmptyRecyclerView()

        }

        binding.paymentBtn.setOnClickListener {
            findNavController().navigate(R.id.selectPaymentFragment)
        }

        //onItemClick()
    }

    //sets up the recyclerview
    private fun setupRecyclerView() {
        paymentModeAdapter = PaymentModeAdapter(paymentModes, viewModel)
        paymentModeAdapter.onItemClick = { paymentMode ->
            Log.d("namba", "${viewModel.userInput.value}")
            findNavController().navigate(R.id.dashboardMainFragment)
        }
        binding.addPaymentRecyclerview.adapter = paymentModeAdapter
        binding.addPaymentRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }



    //checks if the rcyclerview is empty
    private fun handleEmptyRecyclerView() {
        if (paymentModes.isEmpty()) {
            binding.addPaymentRecyclerview.visibility = View.GONE
            binding.constraint.visibility = View.VISIBLE
        } else {
            binding.addPaymentRecyclerview.visibility = View.VISIBLE
            binding.constraint.visibility = View.GONE
        }
    }
}