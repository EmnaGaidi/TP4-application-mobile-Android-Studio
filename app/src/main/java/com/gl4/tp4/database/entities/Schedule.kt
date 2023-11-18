package com.gl4.tp4.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(@PrimaryKey val id: Int,
                    @ColumnInfo(name = "stop_name") val stop_name: String,
                    @ColumnInfo(name = "arrival_time") val arrival_time: Int)
