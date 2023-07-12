package com.ekenya.rnd.common.model

import android.os.Parcel
import android.os.Parcelable

class ParkingResponse : ArrayList<ParkingResponseItem>()


data class ParkingResponseItem(
    val id: Int,
    val image: String?,
    val title: String?,
    val price: Double,
    val location: String?,
    val description: String?,
    val security: String?,
    val about: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(location)
        parcel.writeString(description)
        parcel.writeString(security)
        parcel.writeString(about)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParkingResponseItem> {
        override fun createFromParcel(parcel: Parcel): ParkingResponseItem {
            return ParkingResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<ParkingResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}