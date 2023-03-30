package com.example.currencies.data.remote.dto

import com.example.currencies.domain.model.Currency
import com.google.gson.annotations.SerializedName

/**
 * The CurrencyDto data class represents the response received from the NBRB API for a single currency exchange rate.
 *
 * @property curID  the ID of the currency as an integer.
 * @property curName  the name of the currency as a string
 * @property curOfficialRate  the official exchange rate of the currency as a double.
 * @property curAbbreviation  the abbreviation of the currency as a string.
 * @property date  the date of the exchange rate as a string in the format "yyyy-MM-dd".
 * @property curScale  the scale of the currency as an integer.
 */

data class CurrencyDto(

    @field:SerializedName("Cur_ID")
    val curID: Int,

    @field:SerializedName("Cur_Name")
    val curName: String,

    @field:SerializedName("Cur_OfficialRate")
    val curOfficialRate: Double,

    @field:SerializedName("Cur_Abbreviation")
    val curAbbreviation: String,

    @field:SerializedName("Date")
    val date: String,

    @field:SerializedName("Cur_Scale")
    val curScale: Int
)

/**
 * Extension function converts a [CurrencyDto] object to a [Currency] object
 *
 * @return [Currency]
 */
fun CurrencyDto.toCurrency(): Currency {
    return Currency(
        curID = curID,
        curName = curName,
        curOfficialRate = curOfficialRate,
        curAbbreviation = curAbbreviation,
        curScale = curScale,
        tomorrowRate = curOfficialRate
    )
}
