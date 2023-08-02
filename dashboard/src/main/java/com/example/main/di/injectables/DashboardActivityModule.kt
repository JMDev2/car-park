package com.example.main.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.example.main.ui.MainActivity
import com.example.main.ui.dashboard.MainDashboardViewModel
import com.example.main.ui.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DashboardActivityModule {

    @ContributesAndroidInjector(modules = [MainDashboardActivityModule::class])
    abstract fun contributeDashboardActivity(): MainActivity

    @Module
    abstract class MainDashboardActivityModule{
        @Binds
        @IntoMap
        @ViewModelKey(MainDashboardViewModel::class)
        abstract fun bindDashboardViewModel(viewModel: MainDashboardViewModel) : ViewModel
    }


}