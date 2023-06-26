package com.ekenya.rnd.baseapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.ekenya.rnd.baseapp.R
import com.ekenya.rnd.baseapp.MyApp
import com.ekenya.rnd.baseapp.databinding.ActivitySplashBinding
import com.ekenya.rnd.baseapp.ui.main.MainFragment
import com.ekenya.rnd.baseapp.ui.main.MainViewModel
import com.ekenya.rnd.common.abstractions.BaseActivity
import javax.inject.Inject


class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    private var mApp: MyApp? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        mApp = application as MyApp
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = mViewModel.getData()

        Log.i("SplashActivity", "=> $data")
        //
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }

        window.setFlags(
            WindowManager.LayoutParams.FLAGS_CHANGED,
            WindowManager.LayoutParams.FLAGS_CHANGED
        )
        Handler(Looper.getMainLooper()).postDelayed({
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }, 7000) // 3000 is the delayed time in milliseconds.
    }
    }

//    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
//        // Fragment Injector should use the Application class
//        // If necessary, I will use AndroidInjector as well as App class (I have not done this time)
//        return (application as TourismApp).supportFragmentInjector()
//    }


