package com.ekenya.rnd.common.api.getparkings

import com.ekenya.rnd.common.model.ParkingResponse
import com.ekenya.rnd.common.model.ParkingResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ParkingService {

    /*
    get all the parkings
     */
    @GET("parkings")
    suspend fun getParkings(
    ): Response<ParkingResponse>

    /*
    item specific
     */
    @GET("parkings")
    suspend fun getParkingItem(
    ) : Response<ParkingResponseItem>
}
