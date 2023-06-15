package com.example.main.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.example.main.ui.MainDashboardFragment
import com.example.main.ui.MainDashboardViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DashboardFragmentModule {

    @ContributesAndroidInjector(modules = [MainDashboardFragmentModule::class])
    abstract fun contributeDashboardFragment(): MainDashboardFragment


    @Module
    abstract class MainDashboardFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(MainDashboardViewModel::class)
        abstract fun bindDashboardViewModel(viewModel: MainDashboardViewModel): ViewModel
    }

}