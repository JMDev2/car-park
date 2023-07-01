package com.example.main.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.example.main.ui.payment.SelectPaymentFragment
import com.example.main.ui.booking.BookingFragment
import com.example.main.ui.booking.BookingViewModel
import com.example.main.ui.dashboard.DashboardMainFragment
import com.example.main.ui.dashboard.MainDashboardViewModel
import com.example.main.ui.parking.ParkingFragment
import com.example.main.ui.parking.ParkingViewModel
import com.example.main.ui.payment.MpesaFragment
import com.example.main.ui.payment.PaymentViewModel
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

    //booking fragement
    @ContributesAndroidInjector(modules = [BookingViewModelModule::class])
    abstract fun contributeBookingFragment(): BookingFragment
    @Module
    abstract class BookingViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(BookingViewModel::class)
        abstract fun bindBookingViewModel(viewModel: BookingViewModel): ViewModel
    }

    //payment fragment
    @ContributesAndroidInjector(modules = [PaymentViewModelModule::class])
    abstract fun contributeSelectPaymentFragment(): SelectPaymentFragment
    @Module
    abstract class PaymentViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(PaymentViewModel::class)
        abstract fun bindBookingViewModel(viewModel: PaymentViewModel): ViewModel
    }

    //mpesa fragment using the selectmpesa fragment
    @ContributesAndroidInjector(modules = [PaymentViewModelModule::class])
    abstract fun contributeMpesaFragment(): MpesaFragment
}