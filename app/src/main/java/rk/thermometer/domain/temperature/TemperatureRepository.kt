package rk.thermometer.domain.temperature

import kotlinx.coroutines.flow.Flow

interface TemperatureRepository {
     fun getTemperature(): Flow<String>
}