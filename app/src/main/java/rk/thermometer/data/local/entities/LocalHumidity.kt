package rk.thermometer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "humidity")
internal data class LocalHumidity(
    @PrimaryKey
    val value: String,
    val lastUpdate: String
)