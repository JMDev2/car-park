package com.example.main.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.example.main.ui.booking.ActiveBookingsFragment
import com.example.main.ui.booking.BookingFragment
import com.example.main.ui.payment.SelectPaymentFragment
import com.example.main.ui.booking.BookingViewModel
import com.example.main.ui.dashboard.DashboardMainFragment
import com.example.main.ui.dashboard.MainDashboardViewModel
import com.example.main.ui.parking.ParkingFragment
import com.example.main.ui.parking.ParkingViewModel
import com.example.main.ui.payment.AddPaymentFragment
import com.example.main.ui.payment.CreditCardFragment
import com.example.main.ui.payment.MpesaFragment
import com.example.main.ui.payment.PaymentViewModel
import com.example.main.ui.profile.OpenCameraFragment
import com.example.main.ui.profile.ProfileFragment
import com.example.main.ui.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DashboardFragmentModule {

    //user profile
    @ContributesAndroidInjector(modules = [ProfileViewModelModule::class])
    abstract fun contributeOpenCameraFragment(): OpenCameraFragment
    @ContributesAndroidInjector(modules = [ProfileViewModelModule::class])
    abstract fun contributeProfileFragment(): ProfileFragment
    @Module
    abstract class ProfileViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(ProfileViewModel::class)
        abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
    }

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

    //active bookings
    @ContributesAndroidInjector(modules = [BookingViewModelModule::class])
    abstract fun contributeActiveBookingFragment(): ActiveBookingsFragment

    @Module
    abstract class BookingViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(BookingViewModel::class)
        abstract fun bindBookingViewModel(viewModel: BookingViewModel): ViewModel
    }

    //payment fragments
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

    @ContributesAndroidInjector(modules = [PaymentViewModelModule::class])
    abstract fun contributeSelectPaymentFragment(): SelectPaymentFragment

    @ContributesAndroidInjector(modules = [PaymentViewModelModule::class])
    abstract fun contributeAddPaymentFragment(): AddPaymentFragment

    @ContributesAndroidInjector(modules = [PaymentViewModelModule::class])
    abstract fun contributeCrediCardFragment(): CreditCardFragment
}

