package com.ekenya.rnd.common.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

object SharedPreferences {

    //get onboarding status
    fun getPaymentStatus(context: Context): Boolean {
        return context.getSharedPreferences("payment", Context.MODE_PRIVATE)
            ?.getBoolean("paymentset", false)!!
    }

    // set onboarding status

    fun setPaymentStatus(context: FragmentActivity?, onboarded: Boolean) {
        return context?.getSharedPreferences("payment", Context.MODE_PRIVATE)?.edit()
            ?.putBoolean("paymentset", onboarded)?.apply()!!
    }

}

// Function to set the phone number in SharedPreferences
fun setPhoneNumber(context: Context, phoneNumber: String) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("phoneNumber", phoneNumber)
    editor.apply()
}

// Function to get the phone number from SharedPreferences
fun getPhoneNumber(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("phoneNumber", "")
}