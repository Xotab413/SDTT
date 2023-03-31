package com.example.currencies.common

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Constants used throughout the Currencies app.
 */
object Constants {
    /**
     * The base URL for the National Bank of the Republic of Belarus API.
     */
    const val BASE_URL = "https://www.nbrb.by"

    /**
     * The name of the app's database.
     */
    const val DATABASE_NAME = "Currency"

    /**
     * Keys used for storing currency data in intents and bundles.
     */
    const val CUR_ID = "curId"
    const val CUR_NAME = "curName"
    const val CUR_RATE = "curOfficialRate"
    const val CUR_ABBREVIATION = "curAbbreviation"
    const val CUR_SCALE = "curScale"
    const val TOMORROW_RATE = "tomorrowRate"
    const val IS_SHOWING = "isShowing"
    const val POSITION = "position"
    const val DATE = "date"

    /**
     * Date formats used in the app.
     */
    @SuppressLint("SimpleDateFormat")
    val dateFormatForAPI: DateFormat = SimpleDateFormat("yyyy.MM.dd")
    @SuppressLint("SimpleDateFormat")
    val dateFormatForUI: DateFormat = SimpleDateFormat("dd.MM.yyyy")
}
