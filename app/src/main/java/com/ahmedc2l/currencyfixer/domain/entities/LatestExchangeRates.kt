package com.ahmedc2l.currencyfixer.domain.entities

import android.os.Parcel
import android.os.Parcelable

data class LatestExchangeRates(
    val date: String,
    val countries: List<Country>
)

data class Country(
    val currency: String,
    val exchangeRateEUR: Double,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(currency)
        parcel.writeDouble(exchangeRateEUR)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}