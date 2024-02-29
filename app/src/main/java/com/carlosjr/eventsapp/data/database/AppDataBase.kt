package com.carlosjr.eventsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlosjr.eventsapp.data.database.dao.CheckInDAO
import com.carlosjr.eventsapp.data.database.model.CheckInEvents

@Database(entities = [CheckInEvents::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun checkInDao(): CheckInDAO

    companion object {
        private const val DATABASE_NAME = "database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            if (INSTANCE != null) INSTANCE!!
            else synchronized(this) {
                INSTANCE ?: buidDatabase(context).also { INSTANCE = it }
            }

        private fun buidDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}