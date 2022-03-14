package com.ahmedc2l.currencyfixer.app.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedc2l.currencyfixer.data.models.LatestExchangeRatesModel
import com.ahmedc2l.currencyfixer.domain.usecases.GetLatestExchangeRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val getLatestExchangeRatesUseCase: GetLatestExchangeRatesUseCase
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

    private val _latestExchangeRates = MutableLiveData<LatestExchangeRatesModel>()
    val latestExchangeRates: LiveData<LatestExchangeRatesModel>
        get() = _latestExchangeRates

    fun onErrorMessageShown() {
        _error.value = null
    }

    init {
        _latestExchangeRates.value = LatestExchangeRatesModel.getLastSaved()
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}