package com.example.currencies.data.remote.dto

import com.example.currencies.domain.model.Currency
import com.google.gson.annotations.SerializedName

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
