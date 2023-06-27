package com.example.main.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.main.R

import com.example.main.databinding.FragmentMainDashboardBinding


class DashboardMainFragment : Fragment() {
    private lateinit var binding: FragmentMainDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMainDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.queryHint = "Would you like to Park here?"

        //open navigation drawer
//        binding.openNavCardView.setOnClickListener {
//            findNavController().navigate(R.id.navigationDrawerFragment)
//        }




    }


}