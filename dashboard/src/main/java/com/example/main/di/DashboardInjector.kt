package com.example.main.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.ekenya.rnd.baseapp.TourismApp
import com.ekenya.rnd.baseapp.di.BaseModuleInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class DashboardInjector : BaseModuleInjector{

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun inject(app: TourismApp) {
        DaggerDashboardComponent.builder()
            .appComponent(app.appComponent)
            .build()
            .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector
    }

    override fun fragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return fragmentInjector
    }
}