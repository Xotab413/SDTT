package com.example.currencies.data.db

import androidx.room.*
import com.example.currencies.common.Constants
import com.example.currencies.domain.model.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM Currency ORDER BY ${Constants.POSITION}")
    fun getAllCurrencies(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency)
}
