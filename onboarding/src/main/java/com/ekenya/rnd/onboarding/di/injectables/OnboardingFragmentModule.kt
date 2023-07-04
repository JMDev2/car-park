package com.ekenya.rnd.onboarding.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.onboarding.ui.login.LoginFragment
import com.ekenya.rnd.onboarding.ui.login.LoginVerificationFragment
import com.ekenya.rnd.onboarding.ui.login.LoginViewModel
import com.ekenya.rnd.onboarding.ui.login.PasswordFragment
import com.ekenya.rnd.onboarding.ui.onboarding.OnboardingFragment
import com.ekenya.rnd.onboarding.ui.onboarding.OnboardingViewModel
import com.ekenya.rnd.onboarding.ui.signup.SignUpFragment
import com.ekenya.rnd.onboarding.ui.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class OnboardingFragmentModule {

    //onboarding
    @ContributesAndroidInjector(modules = [OnboardingMainFragmentModule::class])
    abstract fun contributeOnboardingFragment(): OnboardingFragment
    @Module
    abstract class OnboardingMainFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(OnboardingViewModel::class)
        abstract fun bindDashboardViewModel(viewModel: OnboardingViewModel): ViewModel
    }

    //login
    @ContributesAndroidInjector(modules = [OnboardingLoginFragmentModule::class])
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [OnboardingLoginFragmentModule::class])
    abstract fun contributeVerificationFragment(): LoginVerificationFragment

    @ContributesAndroidInjector(modules = [OnboardingLoginFragmentModule::class])
    abstract fun contributePasswordFragment(): PasswordFragment

    @Module
    abstract class OnboardingLoginFragmentModule{
        @Binds
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
    }

    //signup
    @ContributesAndroidInjector(modules = [OnboardingSignUpFragmentModule::class])
    abstract fun contributeSignUpFragment(): SignUpFragment
    @Module
    abstract class OnboardingSignUpFragmentModule{
        @Binds
        @IntoMap
        @ViewModelKey(SignUpViewModel::class)
        abstract fun bindSignUpViewModel(viewModel: SignUpViewModel) : ViewModel
    }



}