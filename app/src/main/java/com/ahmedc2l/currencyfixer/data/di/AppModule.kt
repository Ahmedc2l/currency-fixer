package com.ahmedc2l.currencyfixer.data.di

import android.content.Context
import android.content.res.Resources
import com.ahmedc2l.currencyfixer.app.main.CurrencyFixerApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) = app as CurrencyFixerApplication

    @Singleton
    @Provides
    fun provideCoroutineDispatcher() = Dispatchers.IO
}