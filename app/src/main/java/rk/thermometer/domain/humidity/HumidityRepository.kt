package rk.thermometer.domain.humidity

import kotlinx.coroutines.flow.Flow

interface HumidityRepository {
     fun getHumidity(): Flow<String>
}