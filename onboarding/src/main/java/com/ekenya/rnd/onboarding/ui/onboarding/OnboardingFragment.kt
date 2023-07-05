package com.ekenya.rnd.onboarding.ui.onboarding

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.adapters.OnBoardingViewPagerAdapter
import com.ekenya.rnd.common.model.OnBoardingData
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentOnboardingBinding
import com.ekenya.rnd.onboarding.databinding.FragmentSignUpBinding
import com.google.android.material.tabs.TabLayout


class OnboardingFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentOnboardingBinding

    var onBoardingViewPagerAdapter : OnBoardingViewPagerAdapter? = null
    val tabLayout : TabLayout? = null
    private var viewPager : ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(inflater, container, false )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle("")
        onboardingData()
        moveNext()
        skipNext()

        setupOnBackPressedCallback()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false) //remove the onback stack arrow


    }



    //disable the onback ack
    private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do nothing to prevent navigating back to the onboarding screen
            }
        })
    }
    private fun skipNext() {
        binding.skipText.setOnClickListener {
//            SharedPreferences.setOnboardingStatus(requireActivity(), true)
//            Log.d("skip button click", SharedPreferences.getOnboardingStatus(requireActivity()).toString())
//            findNavController().navigate(R.id.SignUpFragment)



        }
    }

    private fun moveNext() {
        // set page change listener
        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            private var settled = false
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            @SuppressLint("LongLogTag")
            override fun onPageSelected(position: Int) {
                binding.moveNext.setOnClickListener(null) // Remove existing click listener

                if (position == onBoardingViewPagerAdapter!!.count - 1) {
                    binding.moveNext.text = "Get Started"
                    binding.moveNext.setOnClickListener {
                        Toast.makeText(requireActivity(), "clicked", Toast.LENGTH_SHORT).show()
                       // findNavController().navigate(R.id.signUpFragment)
                    }
                } else {
                    binding.moveNext.text = "Next"
                    binding.moveNext.setOnClickListener {
                        val currentPosition = binding.viewPager.currentItem
                        binding.viewPager.post {
                            binding.viewPager.currentItem = currentPosition + 1
                        }
                    }
                }
            }



            override fun onPageScrollStateChanged(state: Int) {

            }


        })

    }

    private fun onboardingData() {
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(
            OnBoardingData("Make a budget", "Make a monthly small budget to ensure\n" +
                "you are spending your money wisely. \n" +
                "This way you will be able to save", com.ekenya.rnd.common.R.drawable.onboardingone
        )
        )
        onBoardingData.add(
            OnBoardingData("Track your spending", "Easily keep track of your spending as\n" +
                "they happen. Have a record of how\n" +
                "you spend.", com.ekenya.rnd.common.R.drawable.onboardingtwo
        )
        )
        onBoardingData.add(
            OnBoardingData("Get rewarded", "For every penny you\n" +
                    "save you will get 20% airtime reward", com.ekenya.rnd.common.R.drawable.onboardingthree
            )
        )
        Log.d("OnboardingFragment", onBoardingData.size.toString())

        setOnBoardingViewPagerAdapter(onBoardingData)
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){

        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(requireContext(), onBoardingData)
        viewPager = binding.viewPager
        viewPager?.adapter = onBoardingViewPagerAdapter
        binding.tabIndicator.setupWithViewPager(binding.viewPager)
    }



}