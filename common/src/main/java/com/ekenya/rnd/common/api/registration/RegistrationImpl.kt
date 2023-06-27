package com.ekenya.rnd.common.api.registration

import com.ekenya.rnd.common.model.User
import com.ekenya.rnd.common.utils.Resource
import javax.inject.Inject

class RegistrationImpl @Inject constructor(private val api: RegistrationService) {
    suspend fun registerUser(user: User): Resource<User?> {
        val response = api.registerUser(user)
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("Error", null)
        }
    }
}