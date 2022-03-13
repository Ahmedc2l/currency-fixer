package com.ahmedc2l.currencyfixer.domain.usecases

import com.ahmedc2l.currencyfixer.domain.repositories.ExchangeRatesRepository
import javax.inject.Inject

class GetLatestExchangeRatesUseCase @Inject constructor(private val exchangeRatesRepository: ExchangeRatesRepository) {
    suspend operator fun invoke() = exchangeRatesRepository.getLatestExchangeRates()
}