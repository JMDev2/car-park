package com.ekenya.rnd.common.model

import android.os.Parcel
import android.os.Parcelable

class SlotsResponse : ArrayList<SlotsResponseItem>()

data class SlotsResponseItem(
    val id: Int,
    val name: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SlotsResponseItem> {
        override fun createFromParcel(parcel: Parcel): SlotsResponseItem {
            return SlotsResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<SlotsResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}

