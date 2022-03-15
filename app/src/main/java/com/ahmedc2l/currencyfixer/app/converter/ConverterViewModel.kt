package com.ahmedc2l.currencyfixer.app.converter

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedc2l.currencyfixer.data.models.LatestExchangeRatesModel
import com.ahmedc2l.currencyfixer.data.models.toDomainEntity
import com.ahmedc2l.currencyfixer.domain.entities.LatestExchangeRates
import com.ahmedc2l.currencyfixer.domain.usecases.ConvertCurrenciesUseCase
import com.ahmedc2l.currencyfixer.domain.usecases.GetLatestExchangeRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val getLatestExchangeRatesUseCase: GetLatestExchangeRatesUseCase,
    private val convertCurrenciesUseCase: ConvertCurrenciesUseCase
) :
    ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _swapCountries = MutableLiveData<Boolean>()
    val swapCountries: LiveData<Boolean>
        get() = _swapCountries

    private val _latestExchangeRates = MutableLiveData<LatestExchangeRates>()
    val latestExchangeRates: LiveData<LatestExchangeRates>
        get() = _latestExchangeRates

    private val _fromCountryIndex = MutableLiveData<Int?>()
    val fromCountryIndex: LiveData<Int?>
        get() = _fromCountryIndex

    private val _toCountryIndex = MutableLiveData<Int?>()
    val toCountryIndex: LiveData<Int?>
        get() = _toCountryIndex

    private val _amount = MutableLiveData<Int>()
    val amount: LiveData<Int>
        get() = _amount

    private val _resultAmount = MutableLiveData<Double>()
    val resultAmount: LiveData<Double>
        get() = _resultAmount

    fun onErrorMessageShown() {
        _error.value = null
    }

    init {
        LatestExchangeRatesModel.getLastSaved().toDomainEntity().also {
            _latestExchangeRates.value = it
            _fromCountryIndex.value = 0
            _toCountryIndex.value = 0
            _amount.value = 1
        }
        // TODO uncomment when you're done testing the default locally stored exchange rates
//        getLatestExchangeRates()
    }

    private fun getLatestExchangeRates() {
        viewModelScope.launch {
            _loading.postValue(true)
            getLatestExchangeRatesUseCase.invoke().fold({
                _error.postValue(it.toErrorString())
            }, {
                _latestExchangeRates.postValue(it)
            })
            _loading.postValue(false)
        }
    }

    fun convertCurrencies(amount: Int?, fromCountry: Int?, toCountry: Int?) {
        Log.i("TAG", "convertCurrencies: $amount")
        fromCountry?.let { from ->
            toCountry?.let { to ->
                _latestExchangeRates.value?.let { latestExchangeRates ->
                    viewModelScope.launch {
                        convertCurrenciesUseCase.invoke(
                            latestExchangeRates.countries[from],
                            latestExchangeRates.countries[to],
                            amount ?: 0
                        ).fold({
                            _error.postValue(it.toErrorString())
                        }, {
                            _resultAmount.value = it
                        })
                    }
                }
            } ?: _error.postValue("To country is NULL")
        } ?: _error.postValue("From country is NULL")
    }

    fun onAmountTextChange(text: CharSequence?, start: Int, before: Int, count: Int) {
        if (!text.isNullOrEmpty())
            try {
                _amount.value = text.toString().toInt()
            } catch (e: NumberFormatException) {
                _amount.value = 0
            }
        else
            _resultAmount.value = 0.0
    }

    fun onFromCountrySelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        latestExchangeRates.value?.let {
            _fromCountryIndex.value = position
        }
    }

    fun onToCountrySelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        latestExchangeRates.value?.let {
            _toCountryIndex.value = position
        }
    }

    fun onSwapClicked() {
        _swapCountries.value = true
    }

    fun onCountriesSwapped() {
        _swapCountries.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}