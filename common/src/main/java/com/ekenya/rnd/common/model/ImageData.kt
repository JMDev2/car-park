package com.ekenya.rnd.common.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable


data class ImageData(val bitmap: Bitmap?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable(Bitmap::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(bitmap, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageData> {
        override fun createFromParcel(parcel: Parcel): ImageData {
            return ImageData(parcel)
        }

        override fun newArray(size: Int): Array<ImageData?> {
            return arrayOfNulls(size)
        }
    }
}