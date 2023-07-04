package com.ekenya.rnd.onboarding.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.NavHostFragment
import com.ekenya.rnd.onboarding.databinding.FragmentPasswordBinding


class PasswordFragment : Fragment() {
    private lateinit var binding: FragmentPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPasswordBinding.inflate(layoutInflater, container, false)


        val text = "<font color='#505353'>Forgot your Password?</font> <font color='#DE7500'>Resend it</font>"
        binding.loginPasswordDesc.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)


        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        // Enable the back arrow in the toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Set the click listener for the back arrow
        binding.toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
        return binding.root
    }


}