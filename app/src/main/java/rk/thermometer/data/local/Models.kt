package rk.thermometer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import rk.thermometer.data.model.Humidity
import rk.thermometer.data.model.Temperature

@Entity(tableName = "humidity")
data class LocalHumidity(
    @PrimaryKey
    val value: String,
    val lastUpdate: String
)

@Entity(tableName = "temperature")
data class LocalTemperature(
    val value: String,
    val lastUpdate: String
)

fun LocalHumidity.toExternal() =
    Humidity(value = value, lastUpdate = lastUpdate)

fun LocalTemperature.toExternal() =
    Temperature(value = value, lastUpdate = lastUpdate)