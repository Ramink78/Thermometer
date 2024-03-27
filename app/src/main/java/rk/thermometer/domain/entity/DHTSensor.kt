package rk.thermometer.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dht_sensor")
data class DHTSensor(
    @PrimaryKey val temperature: String,
    val humidity: String
)
