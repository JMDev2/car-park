package com.example.main.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.example.main.R
import com.example.main.databinding.FragmentBlankBinding
import com.example.main.databinding.FragmentParkingBinding
import com.example.main.databinding.FragmentSelectPaymentBinding


class
SelectPaymentFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentSelectPaymentBinding

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
        binding.proccedToBookMpesaBtn.setOnClickListener {
            findNavController().navigate(R.id.mpesaFragment)
        }
    }
}

