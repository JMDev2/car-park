package com.example.main.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.LottieDialogFragment
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.ekenya.rnd.common.utils.Status
import com.example.main.R
import com.example.main.adapter.ConfirmExitDialogFragment
import com.example.main.adapter.ParkingAdaptor
import com.example.main.databinding.FragmentMainDashboardBinding
import com.google.android.material.navigation.NavigationView
import java.util.Locale
import javax.inject.Inject


class DashboardMainFragment : BaseDaggerFragment(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentMainDashboardBinding

    private lateinit var parkingAdaptor: ParkingAdaptor
    private var lottieDialogFragment: LottieDialogFragment? = null


    //search parkings
    var filteredParkings: List<ParkingResponseItem> = ArrayList()


    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, factory)[MainDashboardViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.queryHint = "Where would you want to Park?"
        binding.navView.setNavigationItemSelectedListener(this)

        // Set up the toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)

        // Set up the drawer toggle
        val toggle = ActionBarDrawerToggle(
            activity, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close
        )
        toggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        observeParkingData()
        headerViews()

        //perfoming search
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Perform filtering or live search as the user types
                filterParking(newText)
                return true
            }
        })
    }

    //manipulating the header layout views
    private fun headerViews() {
        val header = binding.navView.getHeaderView(0)
        val viewProfile = header.findViewById<TextView>(R.id.nav_view_profile_tv)

        viewProfile.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }
    }

    //filtering the search and handling the item onclick after search
    private fun filterParking(parking: String) {
        val filteredList =
            filteredParkings.filter { it.title?.contains(parking, ignoreCase = true) ?: false }
        val theFilteredParkings = ArrayList(filteredList)

        if (theFilteredParkings.isEmpty()) {
            binding.errorLayout.visibility = View.VISIBLE
        } else {
            binding.errorLayout.visibility = View.GONE
        }

        parkingAdaptor = ParkingAdaptor(theFilteredParkings)
        binding.recyclerView.adapter = parkingAdaptor
        parkingAdaptor.notifyDataSetChanged()

        parkingAdaptor.onItemClick = { parking ->
            val bundle = Bundle()
            bundle.putParcelable("item", parking)

            requireView().findNavController().navigate(
                R.id.parkingFragment,
                bundle
            )
        }
    }


    //on navigations items click
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bookings -> {
                findNavController().navigate(R.id.bookingHistoryFragment)
            }

            R.id.nav_payment -> {
                findNavController().navigate(R.id.addPaymentFragment)
            }

            R.id.nav_logout -> {
                showConfirmExitDialog()

            }
        }
        return true
    }

    private fun showConfirmExitDialog() {
        val confirmExitDialog = ConfirmExitDialogFragment()
        confirmExitDialog.show(parentFragmentManager, "ConfirmExitDialog")
    }


    //  Navigating to parking fragment
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

    // setup the recyclerview
    private fun setRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = parkingAdaptor
        }
    }

    private fun showProgressDialog() {
        LottieDialogFragment.newInstance().show(requireFragmentManager(), "")
    }


    //  observe api data

    private fun observeParkingData() {
        val lottieDialogFragment = LottieDialogFragment.newInstance()

        viewModel.observeLoadingState().observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                // Show the progress dialog
                lottieDialogFragment.setProgressBarVisible(true)
            } else {
                // Hide the progress dialog
                lottieDialogFragment.setProgressBarVisible(false)
            }
        }

        viewModel.observeParkingsLivedata().observe(viewLifecycleOwner) { parkingResponse ->
            when (parkingResponse.status) {
                Status.SUCCESS -> {
                    //TODO: Dismiss the progress bar
                    val parking = parkingResponse.data
                    Log.d("ParkingData", "observeParking: ${parkingResponse.data}")

                    parking?.let {
                        filteredParkings = parking //filter the search
                        parkingAdaptor = ParkingAdaptor(it)
                        setRecyclerView()
                        onClickParking()
                    }
                }

                Status.ERROR -> {
                    // Handle error case
                }

                Status.LOADING -> {
                    // Already handled in the observeLoadingState().observe block
                }
            }
        }
    }

}

