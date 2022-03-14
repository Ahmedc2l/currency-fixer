package com.ahmedc2l.currencyfixer.data.repositories

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.datasources.ExchangeRatesDataSource
import com.ahmedc2l.currencyfixer.data.models.LatestExchangeRatesModel
import com.ahmedc2l.currencyfixer.data.utils.AppFailure
import com.ahmedc2l.currencyfixer.domain.repositories.ExchangeRatesRepository
import javax.inject.Inject

class ExchangeRatesRepositoryImpl @Inject constructor(private val exchangeRatesDataSource: ExchangeRatesDataSource) :
    ExchangeRatesRepository {
    override suspend fun getLatestExchangeRates(): Either<AppFailure, LatestExchangeRatesModel> =
        exchangeRatesDataSource.getLatestExchangeRates()
}