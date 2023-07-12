package com.example.main.repository

import com.ekenya.rnd.common.api.getparkings.ParkingImpl
import com.ekenya.rnd.common.api.getparkings.ParkingService
import com.ekenya.rnd.common.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ParkingRepository @Inject constructor(private val api: ParkingImpl){

    suspend fun getParkings() = flow {
        emit(Resource.loading(null))
        emit(api.getAllParkings())
    }.flowOn(Dispatchers.IO)


    suspend fun getParkingItem() = flow {
        emit(Resource.loading(null))
        emit(api.getParkingItem())
    }.flowOn(Dispatchers.IO)
}