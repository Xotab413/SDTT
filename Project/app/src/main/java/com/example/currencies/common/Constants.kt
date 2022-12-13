package com.example.currencies.common

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat

object Constants {
    const val BASE_URL = "https://www.nbrb.by"

    const val DATABASE_NAME = "Currency"

    const val CUR_ID = "curId"
    const val CUR_NAME = "curName"
    const val CUR_RATE = "curOfficialRate"
    const val CUR_ABBREVIATION = "curAbbreviation"
    const val CUR_SCALE = "curScale"
    const val TOMORROW_RATE = "tomorrowRate"
    const val IS_SHOWING = "isShowing"
    const val POSITION = "position"
    const val DATE = "date"

    @SuppressLint("SimpleDateFormat")
    val dateFormatForAPI: DateFormat = SimpleDateFormat("yyyy.MM.dd")
    @SuppressLint("SimpleDateFormat")
    val dateFormatForUI: DateFormat = SimpleDateFormat("dd.MM.yyyy")
}
