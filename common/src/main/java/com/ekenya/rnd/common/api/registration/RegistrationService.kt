package com.ekenya.rnd.common.api.registration

import com.ekenya.rnd.common.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {
    @POST("user")
    suspend fun registerUser(
        @Body user: User
    ): Response<User>
}