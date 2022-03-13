package com.ahmedc2l.currencyfixer.data.datasources

import arrow.core.Either
import com.ahmedc2l.currencyfixer.data.models.LatestExchangeRatesResponseModel
import com.ahmedc2l.currencyfixer.data.network.FixerNetwork
import com.ahmedc2l.currencyfixer.data.utils.AppFailure
import com.ahmedc2l.currencyfixer.data.utils.FixerNetworkFailure
import com.ahmedc2l.currencyfixer.data.utils.SomethingWentWrongFailure
import com.ahmedc2l.currencyfixer.data.utils.getErrorString
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class ExchangeRatesDataSourceImpl @Inject constructor(private val dispatcher: CoroutineDispatcher) :
    ExchangeRatesDataSource {

    override suspend fun getLatestExchangeRates(): Either<AppFailure, String> = withContext(dispatcher){
        try {
            val response =
                FixerNetwork.fixerApiServices.getLatestExchangeRatesAsync().await().body()
            response?.let {
                val responseString = it.string()
                val responseObject = JSONObject(responseString)
                val isSuccess = responseObject.getBoolean("success")

                if(isSuccess){
                    val gson = Gson()
                    val successResult = gson.fromJson(responseObject.toString(),
                        LatestExchangeRatesResponseModel::class.java)
                    Either.Right(successResult.toString())
                }else
                    Either.Left(FixerNetworkFailure(responseObject.getErrorString()))
            } ?: Either.Left(SomethingWentWrongFailure("No body"))

        }catch (e: Exception){
            return@withContext Either.Left(SomethingWentWrongFailure(e.message))
        }
    }
}