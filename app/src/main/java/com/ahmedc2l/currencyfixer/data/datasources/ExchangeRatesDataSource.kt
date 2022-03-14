package com.ahmedc2l.currencyfixer.data.datasources

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.utils.AppFailure
import com.ahmedc2l.currencyfixer.domain.entities.LatestExchangeRates

interface ExchangeRatesDataSource {
    suspend fun getLatestExchangeRates(): Either<AppFailure, LatestExchangeRates>
}