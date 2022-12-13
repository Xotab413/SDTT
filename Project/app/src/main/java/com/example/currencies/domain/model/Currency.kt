package com.example.currencies.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencies.common.Constants.CUR_ABBREVIATION
import com.example.currencies.common.Constants.CUR_ID
import com.example.currencies.common.Constants.CUR_NAME
import com.example.currencies.common.Constants.CUR_RATE
import com.example.currencies.common.Constants.CUR_SCALE
import com.example.currencies.common.Constants.DATE
import com.example.currencies.common.Constants.IS_SHOWING
import com.example.currencies.common.Constants.POSITION
import com.example.currencies.common.Constants.TOMORROW_RATE

@Entity
data class Currency(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = CUR_ID)
    val curID: Int,
    @ColumnInfo(name = CUR_NAME)
    val curName: String,
    @ColumnInfo(name = CUR_RATE)
    var curOfficialRate: Double,
    @ColumnInfo(name = CUR_ABBREVIATION)
    val curAbbreviation: String,
    @ColumnInfo(name = CUR_SCALE)
    val curScale: Int,
    @ColumnInfo(name = TOMORROW_RATE)
    var tomorrowRate: Double,
    @ColumnInfo(name = IS_SHOWING)
    var isShowing: Boolean = true,
    @ColumnInfo(name = POSITION)
    var position: Int = 28,
    @ColumnInfo(name = DATE)
    var date: String = ""
)
