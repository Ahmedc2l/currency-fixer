package com.ahmedc2l.currencyfixer.domain.repositories

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.utils.AppFailure
import com.ahmedc2l.currencyfixer.domain.entities.Country
import com.ahmedc2l.currencyfixer.domain.entities.LatestExchangeRates

interface ExchangeRatesRepository {
    suspend fun getLatestExchangeRates(): Either<AppFailure, LatestExchangeRates>
    suspend fun convertCurrencies(
        fromCountry: Country,
        toCountry: Country,
        amount: Int
    ): Either<AppFailure, Double>

}