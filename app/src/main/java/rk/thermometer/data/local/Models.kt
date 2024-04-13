package rk.thermometer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import rk.thermometer.data.model.Humidity
import rk.thermometer.data.model.Temperature
import rk.utils.timestampToReadableFormat

@Entity(tableName = "humidity")
data class LocalHumidity(
    val value: String,
    @PrimaryKey
    val lastUpdate: String,
)

@Entity(tableName = "temperature")
data class LocalTemperature(
    val value: String,
    @PrimaryKey
    val lastUpdate: String,
)

fun LocalHumidity.toExternal() =
    Humidity(value = value, lastUpdate = timestampToReadableFormat(lastUpdate))

fun LocalTemperature.toExternal() =
    Temperature(value = value, lastUpdate = timestampToReadableFormat(lastUpdate))