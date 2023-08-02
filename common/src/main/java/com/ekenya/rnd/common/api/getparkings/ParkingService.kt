package com.ekenya.rnd.common.api.getparkings

import com.ekenya.rnd.common.model.ParkingResponse
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.ekenya.rnd.common.model.SlotsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ParkingService {
   // get all the parkings
    @GET("parkings")
    suspend fun getParkings(
    ): Response<ParkingResponse>

    @GET("slots")
    suspend fun getParkingSlot(
    ): Response<SlotsResponse>
}
