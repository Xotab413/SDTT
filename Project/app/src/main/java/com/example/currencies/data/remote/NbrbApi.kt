package com.example.currencies.data.remote

import com.example.currencies.data.remote.dto.CurrencyDto
import com.example.currencies.data.remote.dto.RateShortDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NbrbApi {
    @GET("/api/exrates/rates?periodicity=0")
    suspend fun getCurrencies(@Query("ondate") date: String): List<CurrencyDto>

    @GET("/api/exrates/rates/dynamics/{ID}?")
    suspend fun detDynamics(
        @Path("ID") id: String,
        @Query("startdate") start: String,
        @Query("enddate") end: String
    ): List<RateShortDto>
}
