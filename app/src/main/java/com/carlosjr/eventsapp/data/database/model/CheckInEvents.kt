package com.carlosjr.eventsapp.data.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["title"], unique = true)])
data class CheckInEvents(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isCheckIn: Boolean,
    val title: String?,
)