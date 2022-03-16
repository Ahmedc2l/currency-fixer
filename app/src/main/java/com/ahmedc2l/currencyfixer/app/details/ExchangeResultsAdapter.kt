package com.ahmedc2l.currencyfixer.app.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedc2l.currencyfixer.databinding.ItemExchangeResultBinding
import com.ahmedc2l.currencyfixer.domain.entities.ExchangeResult

class ExchangeResultsAdapter :
    ListAdapter<ExchangeResult, ExchangeResultsAdapter.ExchangeResultViewHolder>(
        ExchangeResultsDiffCallback()
    ) {

    class ExchangeResultViewHolder(private val binding: ItemExchangeResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            result: ExchangeResult
        ) {
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ExchangeResultViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemExchangeResultBinding.inflate(layoutInflater, parent, false)
                return ExchangeResultViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeResultViewHolder =
        ExchangeResultViewHolder.from(parent)

    override fun onBindViewHolder(holder: ExchangeResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ExchangeResultsDiffCallback : DiffUtil.ItemCallback<ExchangeResult>() {
    override fun areItemsTheSame(oldItem: ExchangeResult, newItem: ExchangeResult): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ExchangeResult, newItem: ExchangeResult): Boolean {
        return oldItem.result == newItem.result
    }
}