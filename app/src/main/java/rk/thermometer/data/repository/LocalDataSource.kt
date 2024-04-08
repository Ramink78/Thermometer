package rk.thermometer.data.repository

import kotlinx.coroutines.flow.Flow
import rk.thermometer.data.local.LocalHumidity
import rk.thermometer.data.local.LocalTemperature

interface LocalDataSource {
    fun getHumidity(): Flow<List<LocalHumidity>>
    fun getTemperature(): Flow<List<LocalTemperature>>
    suspend fun updateTemperature(localTemperature: LocalTemperature)
    suspend fun updateHumidity(localHumidity: LocalHumidity)
}