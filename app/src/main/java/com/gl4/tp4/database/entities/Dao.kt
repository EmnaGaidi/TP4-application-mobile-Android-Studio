package com.gl4.tp4.database.entities

import androidx.room.Dao
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM schedule ORDER BY arrival_time")
    fun getAll() : List<Schedule>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName")
    fun getByStopName(stopName : String): List<Schedule>
}