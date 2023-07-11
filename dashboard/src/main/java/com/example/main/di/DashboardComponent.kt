package com.example.main.di

import com.ekenya.rnd.baseapp.di.AppComponent
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.baseapp.di.injectables.ViewModelModule
import com.example.main.di.injectables.DashboardActivityModule
import com.example.main.di.injectables.DashboardFragmentModule
import com.example.main.di.injectables.NetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ModuleScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        DashboardFragmentModule::class,
        DashboardActivityModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)

interface DashboardComponent {
    fun inject(injector: DashboardInjector)
}