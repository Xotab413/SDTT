package com.example.currencies.common

import android.widget.ImageView
import com.example.currencies.R

fun ImageView.setFlagImageFromResource(str: String) {
    this.setImageResource(
        when (str) {
            "USD" -> R.drawable.ic_usd
            "EUR" -> R.drawable.ic_eur
            "RUB" -> R.drawable.ic_rub
            "PLN" -> R.drawable.ic_pln
            "UAH" -> R.drawable.ic_uah
            "CAD" -> R.drawable.ic_cad
            "KWD" -> R.drawable.ic_kwd
            "SGD" -> R.drawable.ic_sgd
            "CHF" -> R.drawable.ic_chf
            "GBP" -> R.drawable.ic_gbp
            "AUD" -> R.drawable.ic_aud
            "BGN" -> R.drawable.ic_bgn
            "NZD" -> R.drawable.ic_nzd
            "DKK" -> R.drawable.ic_dkk
            "ISK" -> R.drawable.ic_isk
            "MDL" -> R.drawable.ic_mdl
            "NOK" -> R.drawable.ic_nok
            "KGS" -> R.drawable.ic_kgs
            "IRR" -> R.drawable.ic_irr
            "CNY" -> R.drawable.ic_cny
            "CZK" -> R.drawable.ic_czk
            "SEK" -> R.drawable.ic_sek
            "KZT" -> R.drawable.ic_kzt
            "TRY" -> R.drawable.ic_resource_try
            "JPY" -> R.drawable.ic_jpy
            "AMD" -> R.drawable.ic_amd
            else -> R.drawable.ic_xdr
        }
    )
}
