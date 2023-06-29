package com.example.main.ui.parking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.main.databinding.FragmentParkingBinding


class ParkingFragment : Fragment() {
    private lateinit var binding: FragmentParkingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentParkingBinding.inflate(inflater, container, false)
        return binding.root
    }


}