package com.example.main.ui.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.example.main.R
import com.example.main.databinding.FragmentActiveBookingsBinding


class ActiveBookingsFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentActiveBookingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentActiveBookingsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}