package com.example.main.ui.payment

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekenya.rnd.common.model.PaymentMode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PaymentViewModel @Inject constructor(): ViewModel() {
    //tODO add the viewmodel code

    private val _userInput = MutableStateFlow<String>("")
    val userInput: StateFlow<String> = _userInput

    fun setUserInput(input: String) {
        _userInput.value = input
    }

    private val _selectedPaymentMode = MutableStateFlow<PaymentMode?>(null)
    val selectedPaymentMode: StateFlow<PaymentMode?> = _selectedPaymentMode

    fun savePaymentMode(paymentMode: PaymentMode) {
        _selectedPaymentMode.value = paymentMode
    }




}


