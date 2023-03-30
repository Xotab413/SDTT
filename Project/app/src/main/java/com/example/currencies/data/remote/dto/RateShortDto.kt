package com.example.currencies.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.jjoe64.graphview.series.DataPoint

/**
 * Data transfer object representing a short period rate of a currency.
 *
 * @property curID The identifier of the currency.
 * @property curOfficialRate The official exchange rate of the currency.
 * @property date The date of the rate in format "YYYY-MM-DD".
 */
data class RateShortDto(

    @field:SerializedName("Cur_ID")
    val curID: Int,

    @field:SerializedName("Cur_OfficialRate")
    val curOfficialRate: Double,

    @field:SerializedName("Date")
    val date: String
)

/**
 * Extension function for [RateShortDto] that converts it to a [DataPoint] object for plotting.
 *
 * @return A [DataPoint] object representing the currency rate on a specific date.
 */
fun RateShortDto.toDataPoint(): DataPoint {
    val day = this.date.substring(8, 10).toDouble() / 100
    val month = this.date.substring(5, 7).toDouble()
    val dob: Double = month + day
    return DataPoint(
        dob,
        this.curOfficialRate
    )
}
