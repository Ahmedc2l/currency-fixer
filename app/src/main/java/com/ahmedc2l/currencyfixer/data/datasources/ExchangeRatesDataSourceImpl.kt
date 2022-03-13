package com.ahmedc2l.currencyfixer.data.datasources

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.models.LatestExchangeRatesResponseModel
import com.ahmedc2l.currencyfixer.data.network.FixerNetwork
import com.ahmedc2l.currencyfixer.data.utils.AppFailure
import com.ahmedc2l.currencyfixer.data.utils.SomethingWentWrongFailure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class ExchangeRatesDataSourceImpl @Inject constructor(private val dispatcher: CoroutineDispatcher) :
    ExchangeRatesDataSource {

    override suspend fun getLatestExchangeRates(): Either<AppFailure, String> = withContext(dispatcher){
        try {
            val response: Response<LatestExchangeRatesResponseModel> =
                FixerNetwork.fixerApiServices.getLatestExchangeRatesAsync().await()

        }catch (e: Exception){
            return@withContext Either.Left(SomethingWentWrongFailure(e.message))
        }
        TODO("Not yet implemented")
    }
}