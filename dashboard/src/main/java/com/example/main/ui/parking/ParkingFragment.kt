package com.example.main.ui.parking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.ekenya.rnd.common.utils.Status
import com.example.main.R
import com.example.main.databinding.FragmentParkingBinding
import com.example.main.ui.booking.BookingFragment
import com.example.main.ui.dashboard.MainDashboardViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject


class ParkingFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentParkingBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, factory)[MainDashboardViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentParkingBinding.inflate(inflater, container, false)

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

        // observeParkingItems()
        receivingParkingItem()


        binding.proceedToBookButton.setOnClickListener {
            findNavController().navigate(R.id.bookingFragment)
        }


        binding.proceedToBookButton.setOnClickListener {
            findNavController().navigate(R.id.bookingFragment)
        }
    }

    private fun receivingParkingItem() {
        val item = requireArguments().getParcelable<ParkingResponseItem>("item")
        // Update UI with item details
        item?.let { parkingItem ->
            binding.parkingTitleTv.text = parkingItem.title
            binding.parkingLocationTv.text = parkingItem.location
            binding.parkingDescriptionTv.text = parkingItem.description
            binding.parkingPriceTv.text = parkingItem.price.toString()
            binding.securityFeatureTv.text = parkingItem.security
            binding.aboutFeatureTv.text = parkingItem.about
            Picasso.get().load(parkingItem.image).into(binding.parkingImage)
        }

        // Pass the item to another fragment
        val bundle = Bundle().apply {
            putParcelable("item", item)
        }
        val bookingFragment = BookingFragment()
        bookingFragment.arguments = bundle

     // Perform the fragment transaction
        requireView().findNavController().navigate(
            R.id.bookingFragment,
            bundle
        )
        // navOptions
    }
}


//    private fun observeParkingItems() {
//        viewModel.observeParkingsLivedata().observe(
//            viewLifecycleOwner
//        ) { parking ->
//            when (parking.status) {
//                Status.SUCCESS -> {
//                    //TODo: Dismiss progress dialog
//                    val response = parking.data
//
//                    response?.let {
//                        binding.parkingTitleTv.text = response[6]
//                        binding.locationText.text = response.location
//                        binding.parkingDescriptionTv.text = response.description
//                        binding.parkingPriceTv.text = response.price.toString()
//                        binding.aboutFeatureTv.text = response.about
//                        binding.securityFeatureTv.text = response.security
//                        Picasso.get().load(response.image).into(binding.parkingImage)
//
//                    }
//
//                }
//
//                Status.ERROR -> {
//
//                }
//
//                Status.LOADING -> {
//
//                }
//            }
//        }
//    }


