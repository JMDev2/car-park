package com.example.main.ui.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.main.R
import com.example.main.databinding.FragmentBookingBinding
import com.example.main.databinding.FragmentBookingHistoryBinding
import com.google.android.material.tabs.TabLayoutMediator


class BookingHistoryFragment : Fragment() {
    private lateinit var binding: FragmentBookingHistoryBinding

    var tabTitle = arrayOf("Active", "Completed")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookingHistoryBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //assigning titls to the tabs

        binding.viewPager.adapter = TabLayoutViewpagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()


    }


}