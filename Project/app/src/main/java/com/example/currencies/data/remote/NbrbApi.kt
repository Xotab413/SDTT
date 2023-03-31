package com.example.currencies.data.remote

import com.example.currencies.data.remote.dto.CurrencyDto
import com.example.currencies.data.remote.dto.RateShortDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface for interacting with the NBRB API.
 */
interface NbrbApi {
    /**
     * Get the currencies for a given date.
     *
     * @param date The date for which to retrieve the currencies.
     * @return A list of CurrencyDto objects.
     */
    @GET("/api/exrates/rates?periodicity=0")
    suspend fun getCurrencies(@Query("ondate") date: String): List<CurrencyDto>

    /**
     * Get the exchange rate dynamics for a given currency between two dates.
     *
     * @param id The ID of the currency to retrieve.
     * @param start The start date for the exchange rate dynamics.
     * @param end The end date for the exchange rate dynamics.
     * @return A list of [RateShortDto] objects.
     */
    @GET("/api/exrates/rates/dynamics/{ID}?")
    suspend fun detDynamics(
        @Path("ID") id: String,
        @Query("startdate") start: String,
        @Query("enddate") end: String
    ): List<RateShortDto>
}
