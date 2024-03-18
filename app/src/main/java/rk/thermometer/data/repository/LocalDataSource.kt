package rk.thermometer.data.repository

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getHumidity(): Flow<String>
    fun getTemperature(): Flow<String>
}