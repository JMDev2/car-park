package com.example.main.ui.parking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.ekenya.rnd.common.model.SlotsResponseItem
import com.ekenya.rnd.common.utils.Status
import com.example.main.R
import com.example.main.adapter.SlotsAdapter
import com.example.main.databinding.FragmentParkingBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject


class ParkingFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentParkingBinding
    private var selectedSlot: SlotsResponseItem? = null

    private lateinit var slotsAdapter: SlotsAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, factory)[ParkingViewModel::class.java]
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

        slotsAdapter = SlotsAdapter(ArrayList()) // Initialize the adapter with an empty list


        // observeParkingItems()
        receivingParkingItem()
        observeSlotsData()
        proceedToBook()


        binding.proceedToBookButton.setOnClickListener {
            findNavController().navigate(R.id.BookingFragment)
        }

        // Set the click listener for the button
        binding.proceedToBookButton.setOnClickListener {
            proceedToBook()
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
    }

    /*
    clicking the slots views
     */
    private fun onSlotItemClick() {
        slotsAdapter.onSlotItemClick = { clickedSlot ->
            // Update the selected item
            selectedSlot = clickedSlot
            slotsAdapter.notifyDataSetChanged()
        }
    }

    private fun proceedToBook() {
        // Check if a slot is selected
        // Check if a slot is selected
        if (selectedSlot != null) {
            // Create the bundle with the selected slot
            val bundle = Bundle()
            bundle.putParcelable("slot", selectedSlot)

            // Navigate to the next fragment with the bundle
            findNavController().navigate(
                R.id.BookingFragment,
                bundle
            )

        } else {
            // Show a toast message indicating that no slot is selected

            Toast.makeText(requireContext(), "Please select a slot", Toast.LENGTH_SHORT).show()
        }
    }

    /*
    observing the data
     */
    private fun setRecyclerView() {
        binding.slotRecyclerview.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = slotsAdapter
        }
    }

    private fun observeSlotsData(){
        viewModel.observeSlotsLivedata().observe(
            viewLifecycleOwner
        ){
                slotsResponse ->
            when (slotsResponse.status){
                Status.SUCCESS ->{
                    //TODO: dismiss the progress bar
                    val slots = slotsResponse.data

                    slots?.let {
                        slotsAdapter = SlotsAdapter(it)
                        setRecyclerView()
                        onSlotItemClick()
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




