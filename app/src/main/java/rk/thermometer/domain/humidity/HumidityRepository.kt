package rk.thermometer.domain.humidity

import kotlinx.coroutines.flow.Flow
import rk.thermometer.data.local.LocalHumidity

interface HumidityRepository {
     fun getHumidity(): Flow<List<LocalHumidity>>
     suspend fun sync()
}