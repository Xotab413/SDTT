package com.example.currencies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencies.common.Constants
import com.example.currencies.domain.model.Currency
import java.util.*

/**
* Room database for the [Currency] entity.
 */
@Database(entities = [Currency::class], version = 6, exportSchema = false)
abstract class CurrencyRoomDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyRoomDatabase? = null
        /**
         * Returns an instance of the [CurrencyRoomDatabase]. If an instance already exists, that instance will be returned.
         * Otherwise, a new instance will be created and returned.
         *
         * @param context The context to be used when creating the database.
         * @return An instance of the [CurrencyRoomDatabase].
         */
        fun getDatabase(context: Context): CurrencyRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyRoomDatabase::class.java,
                    Constants.DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
