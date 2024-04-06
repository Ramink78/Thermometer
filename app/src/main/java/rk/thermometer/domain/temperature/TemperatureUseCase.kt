package rk.thermometer.domain.temperature

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rk.thermometer.data.local.toExternal
import rk.thermometer.data.model.Temperature
import javax.inject.Inject

class TemperatureUseCase @Inject constructor(
    private val temperatureRepository: TemperatureRepository
) {
    fun getTemperature(): Flow<List<Temperature>> {
        return temperatureRepository.getTemperature().map { list ->
            list.map { it.toExternal() }
        }
    }
}