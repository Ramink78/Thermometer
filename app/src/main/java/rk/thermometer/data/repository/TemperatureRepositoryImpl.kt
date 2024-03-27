package rk.thermometer.data.repository

import kotlinx.coroutines.flow.Flow
import rk.thermometer.domain.temperature.TemperatureRepository
import javax.inject.Inject

class TemperatureRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    TemperatureRepository {
    override fun getTemperature(): Flow<String> {
        return localDataSource.getTemperature()
    }
}