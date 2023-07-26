package com.example.main.ui.payment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.example.main.R
import com.example.main.databinding.FragmentQRCodeBinding


class QRCodeFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentQRCodeBinding

    private lateinit var animation: Animation



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQRCodeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  binding.doneBtn.text = getPhoneNumber(requireContext())
        binding.doneBtn.setOnClickListener {
            findNavController().navigate(R.id.dashboardMainFragment)
        }

        animation = AnimationUtils.loadAnimation(requireContext(), com.ekenya.rnd.common.R.anim.animator)

        // Start the animation
        val animationDuration = 3000L // 3 seconds
        val handler = Handler()
        handler.postDelayed({
            // This code will run after the specified delay (3 seconds)
            // Hide the animated view
            hideAnimationView()
            // Show the layout with id constraint
            showLayoutWithConstraint()
        }, animationDuration)

    }

    private fun showLayoutWithConstraint() {
        binding.lottieAnimation.startAnimation(animation)
        binding.lottieAnimation.visibility = View.GONE
    }

    private fun hideAnimationView() {
        binding.constraintLayout.visibility = View.VISIBLE
    }


}