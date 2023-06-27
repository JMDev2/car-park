package com.ekenya.rnd.common.api.registration

import com.ekenya.rnd.common.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface RegistrationService {
    @POST("user/create")
    suspend fun registerUser(
        @Body user: User
    ): Response<User>
}