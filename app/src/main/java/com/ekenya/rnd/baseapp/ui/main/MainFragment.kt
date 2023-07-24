package com.ekenya.rnd.baseapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ekenya.rnd.baseapp.MyApp
import com.ekenya.rnd.baseapp.databinding.FragmentMainBinding
import com.ekenya.rnd.baseapp.di.helpers.activities.ActivityHelperKt
import com.ekenya.rnd.baseapp.di.helpers.activities.AddressableActivity
import com.ekenya.rnd.baseapp.di.helpers.features.FeatureModule
import com.ekenya.rnd.baseapp.di.helpers.features.Modules
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : BaseDaggerFragment() {

    private lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mApp: MyApp

    /*
   installs the onboarding module
    */
    private val module by lazy {
        Modules.FeatureOnboarding.INSTANCE
    }

    private val splitInstallManager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(requireActivity())
    }

    private val listener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                setStatus("DOWNLOADING")
            }

            SplitInstallSessionStatus.INSTALLING -> {
                setStatus("INSTALLING")
            }

            SplitInstallSessionStatus.INSTALLED -> {

                // Enable module immediately
                activity?.let { SplitCompat.install(it) }

                setStatus("${module.name} already installed\nPress start to continue ..")
                lifecycleScope.launch{
                    delay(3000)
                    showFeatureModule(module)
                }

                //
//                binding.startButton.visibility = View.VISIBLE
//                binding.startButton.setOnClickListener{
//                    showFeatureModule(module)
//                }
            }

            SplitInstallSessionStatus.FAILED -> {
                setStatus("FAILED")
            }

            else -> {
                setStatus("Something went wrong. Please try again.")

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        mApp = activity?.application as MyApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMainBinding.inflate(inflater, container, false).also {
            binding = it
            val request = SplitInstallRequest
                .newBuilder()
                .addModule(module.name)
                .build()

            splitInstallManager.startInstall(request)

            setStatus("Start install for ${module.name}")

        }.root

    }

    override fun onStart() {
        super.onStart()
        splitInstallManager.registerListener(listener)
    }

    override fun onPause() {
        splitInstallManager.unregisterListener(listener)
        super.onPause()
    }

    private fun setStatus(label: String) {
        //binding.status.text = label
        Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
    }

    /**
     *
     */
    private fun showFeatureModule(module: FeatureModule) {
        try {
            //Inject
            mApp.addModuleInjector(module)
            //

            this.startActivity(
                ActivityHelperKt.intentTo(
                    requireActivity(),
                    module as AddressableActivity
                )
            )
            //finish();
        } catch (e: Exception) {
            e.message?.let { Log.d("MainFragment", it) };
        }
    }
}