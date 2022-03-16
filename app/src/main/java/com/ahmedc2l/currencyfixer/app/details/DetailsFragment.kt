package com.ahmedc2l.currencyfixer.app.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedc2l.currencyfixer.app.main.MainActivityEventsListener
import com.ahmedc2l.currencyfixer.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

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
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.recyclerViewResults.adapter = ExchangeResultsAdapter()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        DetailsFragmentArgs.fromBundle(requireArguments()).also {
            viewModel.setFromCountry(it.country)
        }

        viewModel.back.observe(viewLifecycleOwner){
            if(it != null && it){
                findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                mainActivityEventsListener.showErrorMessage(it)
                viewModel.onErrorMessageShown()
            }
        }

        return binding.root
    }

}