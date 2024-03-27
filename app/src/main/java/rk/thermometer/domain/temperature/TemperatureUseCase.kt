package rk.thermometer.domain.temperature

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TemperatureUseCase @Inject constructor(private val temperatureRepository: TemperatureRepository) {
    fun getTemperature(): Flow<String> {
        //return temperatureRepository.getTemperature()
        return flow {
            emit("25")
            delay(1000)
            emit("45")
            delay(1000)
            emit("-25")
            delay(1000)
            emit("80")
            delay(1000)
            emit("-20")
        }
    }
}