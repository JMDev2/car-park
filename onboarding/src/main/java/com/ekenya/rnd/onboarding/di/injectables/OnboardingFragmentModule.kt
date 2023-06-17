package com.ekenya.rnd.onboarding.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.onboarding.ui.MainFragment
import com.ekenya.rnd.onboarding.ui.OnboardingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class OnboardingFragmentModule {

    @ContributesAndroidInjector(modules = [OnboardingMainFragmentModule::class])
    abstract fun contributeOnboardingFragment(): MainFragment


    @Module
    abstract class OnboardingMainFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(OnboardingViewModel::class)
        abstract fun bindDashboardViewModel(viewModel: OnboardingViewModel): ViewModel
    }

}