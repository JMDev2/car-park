package com.example.main.ui.payment

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.common.model.MpesaPaymentMode
import com.ekenya.rnd.common.utils.CardMaskUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PaymentViewModel @Inject constructor(): ViewModel() {
    //tODO add the viewmodel code

    private val _phoneInput = MutableStateFlow<String>("")
    val phoneInput: StateFlow<String> = _phoneInput

    private val _cardInput = MutableStateFlow<String>("")
    val cardInput: StateFlow<String> = _cardInput

    fun setPhoneNumberInput(input: String) {
        _phoneInput.value = input
    }

    fun setCardNumberInput(input: String) {
        val maskedInput = CardMaskUtil.maskCardNumber(input)
        _cardInput.value = maskedInput
    }

    private val _selectedMpesaPaymentMode = MutableStateFlow<MpesaPaymentMode?>(null)
    val selectedMpesaPaymentMode: StateFlow<MpesaPaymentMode?> = _selectedMpesaPaymentMode

    fun savePaymentMode(mpesaPaymentMode: MpesaPaymentMode) {
        _selectedMpesaPaymentMode.value = mpesaPaymentMode
    }




}


