package com.example.main.ui.booking

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabLayoutViewpagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2 //returns the number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return ActiveBookingsFragment()
            1 -> return CompletedBookingsFragment()
            else -> return ActiveBookingsFragment()
        }
    }
}