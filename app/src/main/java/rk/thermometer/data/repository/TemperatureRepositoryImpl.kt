package rk.thermometer.data.repository

import kotlinx.coroutines.flow.Flow
import rk.thermometer.data.local.LocalTemperature
import rk.thermometer.data.network.models.toLocal
import rk.thermometer.domain.temperature.TemperatureRepository
import javax.inject.Inject

class TemperatureRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) :
    TemperatureRepository {
    override fun getTemperature(): Flow<List<LocalTemperature>> {
        return localDataSource.getTemperature()
    }

    override suspend fun sync() {
        networkDataSource.getTemperatureFlow().collect {
            localDataSource.updateTemperature(localTemperature = it.toLocal())
        }
    }
}