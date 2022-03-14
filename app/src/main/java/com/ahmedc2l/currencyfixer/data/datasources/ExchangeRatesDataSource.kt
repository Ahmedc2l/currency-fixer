package com.ahmedc2l.currencyfixer.data.datasources

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.models.LatestExchangeRatesModel
import com.ahmedc2l.currencyfixer.data.utils.AppFailure

interface ExchangeRatesDataSource {
    suspend fun getLatestExchangeRates(): Either<AppFailure, LatestExchangeRatesModel>
}