package com.ahmedc2l.currencyfixer.domain.entities

import java.text.DecimalFormat

data class ExchangeResult(
    val fromCountry: Country,
    val toCountry: Country,
    val result: Double
){
    fun getResultString() = "${DecimalFormat("#.###").run { this.format(result) }} ${toCountry.currency}"
}