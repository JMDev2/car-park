package com.ekenya.rnd.onboarding.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ekenya.rnd.common.model.UserDetails
import com.ekenya.rnd.onboarding.database.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    //TODO: add the viewmodel code here
    //get user by phone number
    fun getUserByPhoneNumber(phoneNumber: String): LiveData<UserDetails?> {
        return liveData {
            emit(userRepository.getUserByPhoneNumber(phoneNumber))
        }
    }

    //get passowrd by user phone number
//    fun getUserPasswordByPhoneNumber(phoneNumber: String): LiveData<String?> {
//        return liveData {
//            val password = userRepository.getUserPasswordByPhoneNumber(phoneNumber)
//            emit(password)
//        }
//    }
}