package com.ekenya.rnd.common.api.getparkings

import com.ekenya.rnd.common.model.ParkingResponse
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.ekenya.rnd.common.model.SlotsResponse
import com.ekenya.rnd.common.utils.Resource
import javax.inject.Inject

class ParkingImpl @Inject constructor(private val api: ParkingService) {
    suspend fun getAllParkings(): Resource<ParkingResponse?> {
        val response = api.getParkings()
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("No Response found. Check your network connection", null)
        }
    }

    /*
    a single parking
     */
//    suspend fun getParkingItem() : Resource<ParkingResponseItem?>{
//        val response = api.getParkingItem()
//        return if (response.isSuccessful){
//            Resource.success(response.body())
//        }else{
//            Resource.error("No response found", null)
//        }
//    }

    /*
    get the slots
     */
    suspend fun getSlots() : Resource<SlotsResponse?>{
        val response = api.getParkingSlot()
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("No response found", null)
        }
    }
}