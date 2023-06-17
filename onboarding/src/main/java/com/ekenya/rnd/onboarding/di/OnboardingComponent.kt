package com.ekenya.rnd.onboarding.di

import com.ekenya.rnd.baseapp.di.AppComponent
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.baseapp.di.injectables.ViewModelModule
import com.ekenya.rnd.onboarding.di.injectables.OnboardingActivityModule
import com.ekenya.rnd.onboarding.di.injectables.OnboardingFragmentModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ModuleScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        OnboardingFragmentModule::class,
        OnboardingActivityModule::class,
        //MainViewModelModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class
    ]
)

interface OnboardingComponent {
    fun inject(injector: OnboardingInjector)
}