package com.ekenya.rnd.common.repo

import com.ekenya.rnd.common.api.registration.RegistrationImpl
import com.ekenya.rnd.common.model.User
import com.ekenya.rnd.common.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRegistrationRepository @Inject constructor(private val api: RegistrationImpl) {
    suspend fun createUser(user: User) = flow {
        emit(Resource.loading(null))
        emit(api.registerUser(user))
    }.flowOn(Dispatchers.IO)
}