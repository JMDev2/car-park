package com.ekenya.rnd.onboarding.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.onboarding.ui.MainActivity
import com.ekenya.rnd.onboarding.ui.onboarding.OnboardingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class OnboardingActivityModule {

    @ContributesAndroidInjector(modules = [OnboardingMainActivityModule::class])
    abstract fun contributeOnboardingActivity(): MainActivity



    @Module
    abstract class OnboardingMainActivityModule{
        @Binds
        @IntoMap
        @ViewModelKey(OnboardingViewModel::class)
        abstract fun bindOnboardingViewModel(viewModel: OnboardingViewModel) : ViewModel
    }

}