package com.ekenya.rnd.common.api.registration

import com.ekenya.rnd.common.model.UserDetails
import com.ekenya.rnd.common.utils.Resource
import javax.inject.Inject

class RegistrationImpl @Inject constructor(private val api: RegistrationService) {
    suspend fun registerUser(userDetails: UserDetails): Resource<UserDetails?> {
        val response = api.registerUser(userDetails)
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("Error", null)
        }
    }
}