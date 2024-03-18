package rk.thermometer.data.database

import kotlinx.coroutines.flow.Flow

interface DHTSensorDao {
    fun getTemperature(): Flow<String>
    fun getHumidity(): Flow<String>
}