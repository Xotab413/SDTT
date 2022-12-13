package com.example.currencies.presentation.currencies_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencies.common.setFlagImageFromResource
import com.example.currencies.databinding.CurrencyItemBinding
import com.example.currencies.domain.model.Currency

class CurrenciesAdapter(private val onItemClicked: (Currency) -> Unit) :
    ListAdapter<Currency, CurrenciesAdapter.CurrenciesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        return CurrenciesViewHolder(
            CurrencyItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            ),
            onItemClicked
        )
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class CurrenciesViewHolder(
        private var binding: CurrencyItemBinding,
        private val onItemClicked: (Currency) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            itemView.setOnClickListener {
                onItemClicked(currency)
            }
            binding.apply {
                imageView.setFlagImageFromResource(currency.curAbbreviation)
                currencyAbbreviation.text = currency.curAbbreviation
                currencyName.text = "${currency.curScale} ${currency.curName}"
                currencyRateToday.text = currency.curOfficialRate.toString()
                currencyRateTomorrow.text = currency.tomorrowRate.toString()
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Currency>() {
            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
                return oldItem.curID == newItem.curID
            }
        }
    }
}
