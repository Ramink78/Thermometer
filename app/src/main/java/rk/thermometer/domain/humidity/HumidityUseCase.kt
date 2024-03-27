package rk.thermometer.domain.humidity

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HumidityUseCase @Inject constructor(private val humidityRepository: HumidityRepository) {
    fun getHumidity(): Flow<String> {
        return humidityRepository.getHumidity()
    }
}