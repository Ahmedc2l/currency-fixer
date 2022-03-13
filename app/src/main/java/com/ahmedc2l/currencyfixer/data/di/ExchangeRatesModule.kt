package com.ahmedc2l.currencyfixer.data.di

import com.ahmedc2l.currencyfixer.data.datasources.ExchangeRatesDataSource
import com.ahmedc2l.currencyfixer.data.datasources.ExchangeRatesDataSourceImpl
import com.ahmedc2l.currencyfixer.data.repositories.ExchangeRatesRepositoryImpl
import com.ahmedc2l.currencyfixer.domain.repositories.ExchangeRatesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ExchangeRatesModule {
    @Singleton
    @Provides
    fun provideExchangeRatesDataSource(dispatcher: CoroutineDispatcher): ExchangeRatesDataSource =
        ExchangeRatesDataSourceImpl(dispatcher)

    @Singleton
    @Provides
    fun provideExchangeRatesRepository(exchangeRatesDataSource: ExchangeRatesDataSource): ExchangeRatesRepository =
        ExchangeRatesRepositoryImpl(exchangeRatesDataSource)

}