package com.ahmedc2l.currencyfixer.app.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmedc2l.currencyfixer.app.main.MainActivityEventsListener
import com.ahmedc2l.currencyfixer.databinding.FragmentConverterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private val viewModel: ConverterViewModel by viewModels()

    private val mainActivityEventsListener: MainActivityEventsListener by lazy {
        requireNotNull(activity) {
            "Activity must not be null"
        }
        activity as MainActivityEventsListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentConverterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it != null && it)
                mainActivityEventsListener.showLoading()
            else
                mainActivityEventsListener.hideLoading()
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                mainActivityEventsListener.showErrorMessage(it)
                viewModel.onErrorMessageShown()
            }
        }
        viewModel.latestExchangeRates.observe(viewLifecycleOwner) {
            if (it != null) {
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    it.countries.map { country -> country.currency }
                ).also { arrayAdapter ->
                    binding.spinnerFrom.adapter = arrayAdapter
                    binding.spinnerTo.adapter = arrayAdapter
                }
            }
        }
        viewModel.amount.observe(viewLifecycleOwner){
            viewModel.convertCurrencies(it, viewModel.fromCountry.value, viewModel.toCountry.value)
        }
        viewModel.fromCountry.observe(viewLifecycleOwner){
            viewModel.convertCurrencies(viewModel.amount.value, it, viewModel.toCountry.value)
        }
        viewModel.toCountry.observe(viewLifecycleOwner){
            viewModel.convertCurrencies(viewModel.amount.value, viewModel.fromCountry.value, it)
        }
        viewModel.swapCountries.observe(viewLifecycleOwner){
            if(it != null && it){
                val from = viewModel.fromCountry.value
                val to = viewModel.toCountry.value

                viewModel.latestExchangeRates.value?.let { latestExchangeRates ->
                    val indexOfFrom = latestExchangeRates.countries.indexOf(from)
                    val indexOfTo = latestExchangeRates.countries.indexOf(to)

                    if(indexOfFrom >= 0)
                        binding.spinnerFrom.setSelection(indexOfTo)
                    if(indexOfTo >= 0)
                        binding.spinnerTo.setSelection(indexOfFrom)
                }

                viewModel.onCountriesSwapped()
            }
        }

        return binding.root
    }
}