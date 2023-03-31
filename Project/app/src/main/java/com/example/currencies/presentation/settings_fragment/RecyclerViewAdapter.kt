package com.example.currencies.presentation.settings_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencies.common.setFlagImageFromResource
import com.example.currencies.databinding.CurrencySettingCardBinding
import com.example.currencies.domain.model.Currency


/**
 * [RecyclerViewAdapter] is a class that extends [ListAdapter] to provide a flexible
 * and efficient way to display a list of items in a RecyclerView.
 * It is responsible for creating ViewHolder objects and binding ViewHolder
 * contents with the data to be displayed in the RecyclerView.
 *
 * @param DiffCallback A callback used to calculate the difference between two
 * lists and output a list of update operations that can convert the first list into the second one.
 */
class RecyclerViewAdapter :
    ListAdapter<Currency, RecyclerViewAdapter.RecViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {
        return RecViewHolder(
            CurrencySettingCardBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {
        val current = getItem(position)
        current.position = position
        holder.bind(current)
    }

    class RecViewHolder(
        private var binding: CurrencySettingCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency) {
            binding.apply {
                flag.setFlagImageFromResource(currency.curAbbreviation)
                abbreviation.text = currency.curAbbreviation
                smallAbbreviation.text = currency.curName
                switchComponent.isChecked = currency.isShowing
                switchComponent.setOnCheckedChangeListener { _, b ->
                    currency.isShowing = b
                }
            }
        }
    }

    companion object {

        /**
         * A DiffUtil.ItemCallback object that defines how to compare two [Currency] objects
         * for the purpose of calculating a list of update operations that can convert the
         * first list into the second one.
         */
        private val DiffCallback = object : DiffUtil.ItemCallback<Currency>() {
            override fun areItemsTheSame(
                oldItem: Currency,
                newItem: Currency
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: Currency,
                newItem: Currency
            ): Boolean {
                return oldItem.curAbbreviation == newItem.curAbbreviation
            }
        }
    }
}
