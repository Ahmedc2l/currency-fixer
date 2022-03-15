package com.ahmedc2l.currencyfixer.domain.usecases

import com.ahmedc2l.currencyfixer.domain.entities.Country
import com.ahmedc2l.currencyfixer.domain.repositories.ExchangeRatesRepository
import javax.inject.Inject

class ConvertCurrenciesUseCase @Inject constructor(private val exchangeRatesRepository: ExchangeRatesRepository) {
    suspend operator fun invoke(fromCountry: Country, toCountry: Country, amount: Int) =
        exchangeRatesRepository.convertCurrencies(fromCountry, toCountry, amount)
}