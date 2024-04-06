package rk.thermometer.domain.humidity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rk.thermometer.data.local.toExternal
import rk.thermometer.data.model.Humidity
import javax.inject.Inject

class HumidityUseCase @Inject constructor(
    private val humidityRepository: HumidityRepository
) {
    fun getHumidity(): Flow<List<Humidity>> {
        return humidityRepository.getHumidity().map { list -> list.map { it.toExternal() } }
    }
}