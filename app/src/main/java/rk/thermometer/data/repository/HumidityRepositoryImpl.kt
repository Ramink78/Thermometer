package rk.thermometer.data.repository

import kotlinx.coroutines.flow.Flow
import rk.thermometer.domain.humidity.HumidityRepository
import javax.inject.Inject

class HumidityRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    HumidityRepository {
    override fun getHumidity(): Flow<String> {
        return localDataSource.getHumidity()
    }
}