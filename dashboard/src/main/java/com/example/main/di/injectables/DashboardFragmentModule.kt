package com.example.main.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.example.main.ui.dashboard.DashboardMainFragment
import com.example.main.ui.dashboard.MainDashboardViewModel
import com.example.main.ui.parking.ParkingFragment
import com.example.main.ui.parking.ParkingViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DashboardFragmentModule {

    //dashboard fragment
    @ContributesAndroidInjector(modules = [MainDashboardFragmentModule::class])
    abstract fun contributeDashboardFragment(): DashboardMainFragment
    @Module
    abstract class MainDashboardFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(MainDashboardViewModel::class)
        abstract fun bindDashboardViewModel(viewModel: MainDashboardViewModel): ViewModel
    }

    //parking fragment
    @ContributesAndroidInjector(modules = [ParkingViewModelModule::class])
    abstract fun contributeParkingFragment(): ParkingFragment
    @Module
    abstract class ParkingViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(ParkingViewModel::class)
        abstract fun bindDashboardViewModel(viewModel: ParkingViewModel): ViewModel
    }

}