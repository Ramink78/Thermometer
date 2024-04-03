package rk.thermometer.data.local.entities

import androidx.room.Entity

@Entity(tableName = "temperature")
internal data class LocalTemperature(
    val value: String,
    val lastUpdate: String
)