package com.ekenya.rnd.common.model

class ParkingResponse : ArrayList<ParkingResponseItem>()

data class ParkingResponseItem(
    val id: Int,
    val image: String,
    val title: String,
    val price: Double,
    val location: String,
)