package com.carlosjr.eventsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlosjr.eventsapp.data.database.model.CheckInEvents

@Dao
interface CheckInDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checkInEvents: CheckInEvents)

    @Query("SELECT*FROM CheckInEvents")
    suspend fun searchAll(): List<CheckInEvents>

}