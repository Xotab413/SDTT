package com.example.currencies.data.db

import androidx.room.*
import com.example.currencies.common.Constants
import com.example.currencies.domain.model.Currency

/**
* Data Access Object (DAO) for the [Currency] entity.
 * */
@Dao
interface CurrencyDao {
    /**
     * Get all currencies as list
     *
     * @return list of all [Currency] objects stored in the database, ordered by their [Constants.POSITION].
     */
    @Query("SELECT * FROM Currency ORDER BY ${Constants.POSITION}")
    fun getAllCurrencies(): List<Currency>

    /**
    * Inserts a [Currency] object into the database.
    * If a [Currency] object with the same primary key already exists,
    * the old object will be replaced with the new one.
    * @param currency The [Currency] object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency)
}
