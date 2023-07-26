package com.ekenya.rnd.onboarding.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.model.UserDetails
import com.ekenya.rnd.common.model.UserResponse
import com.ekenya.rnd.onboarding.repo.UserRegistrationRepository
import com.ekenya.rnd.common.utils.Resource
import kotlinx.coroutines.launch
import java.security.MessageDigest
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val repository: UserRegistrationRepository): ViewModel(){
    //TODO: add viewmodel code here
    private val registerUserLiveDataDetails = MutableLiveData<Resource<UserDetails?>>()

    private val userResponseLiveData = MutableLiveData<Resource<UserResponse?>>()


    fun registerUser(userDetails: UserDetails) = viewModelScope.launch {
        repository.createUser(userDetails).collect { response ->
            registerUserLiveDataDetails.setValue(response)
        }
    }

    fun observeRegisterUserLiveData(): LiveData<Resource<UserResponse?>>{
        return userResponseLiveData
    }

    //harshing
    fun getHas(plainText: String, algorithm : String): String {
        val bytes =  MessageDigest .getInstance(algorithm).digest(plainText.toByteArray())
        return toHex(bytes)
    }
    private fun toHex(byteArray: ByteArray): String {
         Log.d( "viewModel" , byteArray.joinToString { "%02x" .format(it)})
        return byteArray .joinToString {  "%02x". format(it) }
    }
}