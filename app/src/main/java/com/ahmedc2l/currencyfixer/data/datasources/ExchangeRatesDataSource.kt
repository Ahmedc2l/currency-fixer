package com.ahmedc2l.currencyfixer.data.datasources

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.utils.AppFailure
import com.ahmedc2l.currencyfixer.domain.entities.Country
import com.ahmedc2l.currencyfixer.domain.entities.LatestExchangeRates

interface ExchangeRatesDataSource {
    suspend fun getLatestExchangeRates(): Either<AppFailure, LatestExchangeRates>
    suspend fun convertCurrencies(
        fromCountry: Country,
        toCountry: Country,
        amount: Int
    ): Either<AppFailure, Double>
}