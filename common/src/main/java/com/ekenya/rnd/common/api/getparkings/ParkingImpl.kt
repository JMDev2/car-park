package com.ekenya.rnd.common.api.getparkings

import com.ekenya.rnd.common.model.ParkingResponse
import com.ekenya.rnd.common.utils.Resource
import javax.inject.Inject

class ParkingImpl @Inject constructor(private val api: ParkingService) {
    suspend fun getAllParkings(): Resource<ParkingResponse?> {
        val response = api.getParkings()
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("No Campaigns found. Check your network connection", null)
        }
    }
}