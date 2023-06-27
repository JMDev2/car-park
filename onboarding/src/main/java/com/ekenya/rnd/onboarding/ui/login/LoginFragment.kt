package com.ekenya.rnd.onboarding.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.baseapp.MyApp
import com.ekenya.rnd.baseapp.di.helpers.activities.ActivityHelperKt
import com.ekenya.rnd.baseapp.di.helpers.activities.AddressableActivity
import com.ekenya.rnd.baseapp.di.helpers.features.FeatureModule
import com.ekenya.rnd.baseapp.di.helpers.features.Modules
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.toast
import com.ekenya.rnd.onboarding.R
import com.ekenya.rnd.onboarding.databinding.FragmentLoginBinding
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus


class LoginFragment : BaseDaggerFragment() {
    private val TAG = "Mod Test Again"
    private lateinit var binding: FragmentLoginBinding

    private lateinit var mApp: MyApp


    private val module by lazy {
        Modules.FeatureDashboard.INSTANCE
    }

    private val splitInstallManager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(requireActivity())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mApp = activity?.application as MyApp

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = SplitInstallRequest
            .newBuilder()
            .addModule(module.name)
            .build()

        splitInstallManager.startInstall(request)
            .addOnFailureListener {
                Log.e(TAG, "onViewCreated: installing module failed")
            }
            .addOnSuccessListener {
                Log.d(TAG, "onViewCreated: module installed successfully")
            }


        binding.loginButton.setOnClickListener {
            showFeatureModule(module)

        }
    }

    private fun showFeatureModule(module: FeatureModule) {
        try {
            //Inject
            mApp.addModuleInjector(module)

            this.startActivity(
                ActivityHelperKt.intentTo(
                    requireActivity(),
                    module as AddressableActivity
                )
            )
            //finish();
        } catch (e: Exception) {
            e.message?.let { Log.d("error feature module", it) }
        }
    }

    private val listener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.INSTALLED -> {
                toast("Installed here")
            }

            SplitInstallSessionStatus.INSTALLING -> {
                toast("installing here")

            }

            SplitInstallSessionStatus.FAILED -> {
                toast("failing here")

            }

            else -> {
                toast("Something went wrong")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        splitInstallManager.registerListener(listener)
    }

    override fun onPause() {
        super.onPause()
        splitInstallManager.unregisterListener(listener)
    }

}