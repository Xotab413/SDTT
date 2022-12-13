package com.example.currencies.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencies.common.Constants
import com.example.currencies.domain.model.Currency
import java.util.*

@Database(entities = [Currency::class], version = 6, exportSchema = false)
abstract class CurrencyRoomDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyRoomDatabase? = null

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
