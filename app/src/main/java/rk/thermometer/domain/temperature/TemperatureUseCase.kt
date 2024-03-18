package rk.thermometer.domain.temperature

import kotlinx.coroutines.flow.Flow

class TemperatureUseCase(private val temperatureRepository: TemperatureRepository) {
    fun getTemperature(): Flow<String> {
        return temperatureRepository.getTemperature()
    }
}