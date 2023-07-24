package com.ekenya.rnd.onboarding.database

import com.ekenya.rnd.common.model.UserDetails
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    fun readUsers() = userDao.getAllUsers()

    suspend fun addUser(userDetails: UserDetails){
        userDao.saveUser(userDetails)
    }

    //get user by phone number
    suspend fun getUserByPhoneNumber(phoneNumber: String): UserDetails? {
        return userDao.getUserByPhoneNumber(phoneNumber)
    }

//    suspend fun getUserPasswordByPhoneNumber(phoneNumber: String): String? {
//        val password = userDao.getPasswordByPhoneNumber(phoneNumber)
//        return password
//    }

}