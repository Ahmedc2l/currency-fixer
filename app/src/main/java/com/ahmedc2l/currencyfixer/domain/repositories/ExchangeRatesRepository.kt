package com.ahmedc2l.currencyfixer.domain.repositories

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.utils.AppFailure
import com.ahmedc2l.currencyfixer.domain.entities.LatestExchangeRates

interface ExchangeRatesRepository {
    suspend fun getLatestExchangeRates(): Either<AppFailure, LatestExchangeRates>
}