package com.ahmedc2l.currencyfixer.domain.entities

data class LatestExchangeRates(
    val dateTime: Long,
    val countries: List<Country>
)

data class Country(
    val currency: String,
    val exchangeRateEUR: Double,
)