package com.ekenya.rnd.common.model

import android.os.Parcel
import android.os.Parcelable

data class CardPaymentMode(
    val name: String?,
    val image: Int,
    val cardNumber: String?
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
        parcel.writeString(cardNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardPaymentMode> {
        override fun createFromParcel(parcel: Parcel): CardPaymentMode {
            return CardPaymentMode(parcel)
        }

        override fun newArray(size: Int): Array<CardPaymentMode?> {
            return arrayOfNulls(size)
        }
    }
}
