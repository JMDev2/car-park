package com.example.main.ui.dashboard

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.ekenya.rnd.common.utils.Status
import com.example.main.R
import com.example.main.adapter.ParkingAdaptor
import com.example.main.databinding.FragmentMainDashboardBinding
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject


class DashboardMainFragment : BaseDaggerFragment(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentMainDashboardBinding

    private lateinit var parkingAdaptor: ParkingAdaptor

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, factory)[MainDashboardViewModel::class.java]
    }

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

        observeParkingData()




    }

    //on navigations items click
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bookings -> {
                findNavController().navigate(R.id.bookingHistoryFragment)
            }
            R.id.nav_settings -> {
                findNavController().navigate(R.id.blankFragment)
            }

            R.id.nav_payment -> {
                findNavController().navigate(R.id.selectPaymentFragment)
            }

            R.id.nav_logout -> {
                findNavController().navigate(R.id.blankFragment)
            }


        }
        return true
    }

    /*
    Navigating to parking fragment
     */

    private fun onClickParking() {
        parkingAdaptor.onItemClick = { ParkingResponseItem ->
            val bundle = Bundle()
            bundle.putParcelable("item", ParkingResponseItem)

            requireView().findNavController().navigate(
                R.id.parkingFragment,
                bundle,
               // navOptions
            )
        }
    }


    /*
    setup the recyclerview
     */
    private fun setRecyclerView(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = parkingAdaptor
        }
    }


    /*
    observe api data
     */

    private fun observeParkingData(){
        viewModel.observeParkingsLivedata().observe(
            viewLifecycleOwner)
        { parkingResponse ->
            when (parkingResponse.status){
                Status.SUCCESS ->{
                    //TODO: Dismiss the progress bar
                    val parking = parkingResponse.data
                    Log.d("ParkingData", "observeParking: ${parkingResponse.data}")

                    parking?.let {
                        parkingAdaptor = ParkingAdaptor(it)
                        setRecyclerView()
                        onClickParking()

                    }


                }
                Status.ERROR ->{

                }
                Status.LOADING ->{

                }

            }

        }
    }

}
