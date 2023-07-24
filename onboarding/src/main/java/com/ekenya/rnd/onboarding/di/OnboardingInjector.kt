package com.ekenya.rnd.onboarding.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.ekenya.rnd.baseapp.MyApp
import com.ekenya.rnd.baseapp.di.BaseModuleInjector
import com.ekenya.rnd.onboarding.di.injectables.RoomModule
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject




class OnboardingInjector : BaseModuleInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun inject(app: MyApp) {
        DaggerOnboardingComponent.builder()
            .appComponent(app.appComponent)
            .roomModule(RoomModule(app.applicationContext))
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