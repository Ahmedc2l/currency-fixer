package com.ahmedc2l.currencyfixer.data.models

data class LatestExchangeRatesResponseModel(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)