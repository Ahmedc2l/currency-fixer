package com.ahmedc2l.currencyfixer.data.repositories

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.datasources.ExchangeRatesDataSource
import com.ahmedc2l.currencyfixer.data.utils.AppFailure
import com.ahmedc2l.currencyfixer.domain.entities.Country
import com.ahmedc2l.currencyfixer.domain.entities.LatestExchangeRates
import com.ahmedc2l.currencyfixer.domain.repositories.ExchangeRatesRepository
import javax.inject.Inject

class ExchangeRatesRepositoryImpl @Inject constructor(private val exchangeRatesDataSource: ExchangeRatesDataSource) :
    ExchangeRatesRepository {
    override suspend fun getLatestExchangeRates(): Either<AppFailure, LatestExchangeRates> =
        exchangeRatesDataSource.getLatestExchangeRates()

    override suspend fun convertCurrencies(
        fromCountry: Country,
        toCountry: Country,
        amount: Int
    ): Either<AppFailure, Double> =
        exchangeRatesDataSource.convertCurrencies(fromCountry, toCountry, amount)
}