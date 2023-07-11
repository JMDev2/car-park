package com.ekenya.rnd.common.api.getparkings

import com.ekenya.rnd.common.model.ParkingResponse
import retrofit2.Response
import retrofit2.http.GET

interface ParkingService {

    /*
    get the parkings
     */
    @GET("parkings")
    suspend fun getParkings(
    ): Response<ParkingResponse>
}
