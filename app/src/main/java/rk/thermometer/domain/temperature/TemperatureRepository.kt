package rk.thermometer.domain.temperature

import kotlinx.coroutines.flow.Flow
import rk.thermometer.data.local.LocalTemperature

interface TemperatureRepository {
     fun getTemperature(): Flow<List<LocalTemperature>>
     suspend fun sync()
}