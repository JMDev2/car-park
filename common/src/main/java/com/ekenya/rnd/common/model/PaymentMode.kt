package com.ekenya.rnd.common.model

import android.os.Parcel
import android.os.Parcelable

data class PaymentMode(
    val name: String?,
    val image: Int,
    val phoneNumber: String?
    ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
        parcel.writeString(phoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PaymentMode> {
        override fun createFromParcel(parcel: Parcel): PaymentMode {
            return PaymentMode(parcel)
        }

        override fun newArray(size: Int): Array<PaymentMode?> {
            return arrayOfNulls(size)
        }
    }
}