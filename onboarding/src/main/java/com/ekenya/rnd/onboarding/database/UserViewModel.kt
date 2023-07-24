package com.ekenya.rnd.onboarding.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.model.UserDetails
import kotlinx.coroutines.launch
import java.security.MessageDigest
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _allUsers = userRepository.readUsers()
    val allUsers: LiveData<List<UserDetails>>
        get() = _allUsers

    fun addUser(userDetails: UserDetails) {
        viewModelScope.launch {
            userRepository.addUser(userDetails)
        }
    }



    //harshing
    fun getHash(plainText: String, algorithm: String): String {
        val bytes = MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
        return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray): String {
        return byteArray.joinToString("") { "%02x".format(it) }
    }


}