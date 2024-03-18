package rk.thermometer.domain.humidity

import kotlinx.coroutines.flow.Flow

class HumidityUseCase(private val humidityRepository: HumidityRepository) {
    fun getHumidity(): Flow<String> {
        return humidityRepository.getHumidity()
    }
}