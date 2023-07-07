package com.example.main.ui.payment

import androidx.lifecycle.ViewModel
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




}