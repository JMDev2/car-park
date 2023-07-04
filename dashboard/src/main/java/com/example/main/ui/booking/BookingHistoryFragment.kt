package com.example.main.ui.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
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
        //assigning titls to the tabs

        binding.viewPager.adapter = TabLayoutViewpagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()


    }


}