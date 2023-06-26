package com.ekenya.rnd.common.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

object SharedPreferences {

    //get onboarding status
    fun getOnboardingStatus(context: Context): Boolean {
        return context.getSharedPreferences("profile", Context.MODE_PRIVATE)
            ?.getBoolean("onboarded", false)!!
    }

    // set onboarding status

    fun setOnboardingStatus(context: FragmentActivity?, onboarded: Boolean) {
        return context?.getSharedPreferences("profile", Context.MODE_PRIVATE)?.edit()
            ?.putBoolean("onboarded", onboarded)?.apply()!!
    }

}