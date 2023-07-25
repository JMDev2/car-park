package com.example.main.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.getPhoneNumber
import com.example.main.R
import com.example.main.databinding.FragmentQRCodeBinding


class QRCodeFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentQRCodeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQRCodeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doneBtn.text = getPhoneNumber(requireContext())
        binding.doneBtn.setOnClickListener {
            findNavController().navigate(R.id.dashboardMainFragment)
        }
    }


}