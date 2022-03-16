package com.ahmedc2l.currencyfixer.app.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedc2l.currencyfixer.data.models.LatestExchangeRatesModel
import com.ahmedc2l.currencyfixer.data.models.toDomainEntity
import com.ahmedc2l.currencyfixer.domain.entities.Country
import com.ahmedc2l.currencyfixer.domain.entities.ExchangeResult
import com.ahmedc2l.currencyfixer.domain.usecases.ConvertCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val convertCurrenciesUseCase: ConvertCurrenciesUseCase
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back

    private val _fromCountry = MutableLiveData<Country>()
    val fromCountry: LiveData<Country>
        get() = _fromCountry

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _results = MutableLiveData<List<ExchangeResult>>()
    val results: LiveData<List<ExchangeResult>>
        get() = _results

    val latestExchangeRates = LatestExchangeRatesModel.getLastSaved().toDomainEntity()
    private val toCountries = latestExchangeRates.countries.filter {
        it.currency == "USD"
                || it.currency == "EUR"
                || it.currency == "GBP"
                || it.currency == "CAD"
                || it.currency == "AED"
                || it.currency == "SAR"
                || it.currency == "EGP"
                || it.currency == "JPY"
                || it.currency == "INR"
                || it.currency == "TRY"
    }

    init {
        getResults()
    }

    private fun getResults() {
        viewModelScope.launch {
            val results = mutableListOf<ExchangeResult>()
            toCountries.forEach {
                convertCurrenciesUseCase.invoke(fromCountry.value!!, it, 1).fold({ failure ->
                    _error.postValue(failure.toErrorString())
                }, { result ->
                    results.add(ExchangeResult(fromCountry.value!!, it, result))
                })
            }
            _results.postValue(results)
        }
    }

    fun setFromCountry(country: Country) {
        _fromCountry.value = country
    }

    fun onBackClicked() {
        _back.value = true
    }

    fun onBackNavigated() {
        _back.value = false
    }

    fun onErrorMessageShown() {
        _error.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}