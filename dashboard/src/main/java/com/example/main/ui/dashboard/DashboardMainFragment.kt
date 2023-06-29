package com.example.main.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.main.R
import com.example.main.databinding.FragmentMainDashboardBinding
import com.google.android.material.navigation.NavigationView


class DashboardMainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentMainDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.queryHint="Where would you want to Park?"
        binding.navView.setNavigationItemSelectedListener(this)

        // Set up the toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)

        // Set up the drawer toggle
        val toggle = ActionBarDrawerToggle(
            activity,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open,
            R.string.close
        )
        toggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    //on navigations items click
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bookings -> {
                findNavController().navigate(R.id.blankFragment)
            }
            R.id.nav_settings -> {
                findNavController().navigate(R.id.blankFragment)
            }
            R.id.nav_pricing_policy -> {
                findNavController().navigate(R.id.blankFragment)
            }
            R.id.nav_logout -> {
                findNavController().navigate(R.id.blankFragment)
            }
            R.id.nav_payment -> {
                findNavController().navigate(R.id.blankFragment)
            }
        }
        return true
    }

}
