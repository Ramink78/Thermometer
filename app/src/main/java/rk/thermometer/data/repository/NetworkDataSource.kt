package rk.thermometer.data.repository

import kotlinx.coroutines.flow.Flow
import rk.thermometer.data.network.models.NetworkHumidity
import rk.thermometer.data.network.models.NetworkTemperature

interface NetworkDataSource {
    suspend fun getTemperatureFlow(): Flow<NetworkTemperature>
    suspend fun getHumidityFlow(): Flow<NetworkHumidity>
}