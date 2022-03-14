package com.ahmedc2l.currencyfixer.domain.repositories

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.models.LatestExchangeRatesModel
import com.ahmedc2l.currencyfixer.data.utils.AppFailure

interface ExchangeRatesRepository {
    suspend fun getLatestExchangeRates(): Either<AppFailure, LatestExchangeRatesModel>
}