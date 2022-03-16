package com.ahmedc2l.currencyfixer.app.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedc2l.currencyfixer.app.details.ExchangeResultsAdapter
import com.ahmedc2l.currencyfixer.domain.entities.ExchangeResult

@BindingAdapter("exchangeResults")
fun bindExchangeResultRecyclerView(recyclerView: RecyclerView, data: List<ExchangeResult>?) {
    val adapter = recyclerView.adapter as ExchangeResultsAdapter
    adapter.submitList(data)
}