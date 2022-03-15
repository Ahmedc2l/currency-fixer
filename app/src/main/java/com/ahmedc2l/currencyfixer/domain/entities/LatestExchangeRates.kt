package com.ahmedc2l.currencyfixer.domain.entities

data class LatestExchangeRates(
    val date: String,
    val countries: List<Country>
)

data class Country(
    val currency: String,
    val exchangeRateEUR: Double,
)