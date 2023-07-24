package com.ekenya.rnd.common.api.registration

import com.ekenya.rnd.common.model.UserDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {
    @POST("user")
    suspend fun registerUser(
        @Body userDetails: UserDetails
    ): Response<UserDetails>
}