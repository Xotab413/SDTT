package com.example.currencies.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.jjoe64.graphview.series.DataPoint

data class RateShortDto(

    @field:SerializedName("Cur_ID")
    val curID: Int,

    @field:SerializedName("Cur_OfficialRate")
    val curOfficialRate: Double,

    @field:SerializedName("Date")
    val date: String
)

fun RateShortDto.toDataPoint(): DataPoint {
    val day = this.date.substring(8, 10).toDouble() / 100
    val month = this.date.substring(5, 7).toDouble()
    val dob: Double = month + day
    return DataPoint(
        dob,
        this.curOfficialRate
    )
}
