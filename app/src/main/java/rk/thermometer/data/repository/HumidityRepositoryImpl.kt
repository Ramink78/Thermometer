package rk.thermometer.data.repository

import kotlinx.coroutines.flow.Flow
import rk.thermometer.data.local.LocalHumidity
import rk.thermometer.data.network.models.toLocal
import rk.thermometer.domain.humidity.HumidityRepository
import javax.inject.Inject

class HumidityRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) :
    HumidityRepository {
    override fun getHumidity(): Flow<List<LocalHumidity>> {
        return localDataSource.getHumidity()
    }

    override suspend fun sync() {
        networkDataSource.getHumidityFlow().collect {
            localDataSource.updateHumidity(localHumidity = it.toLocal())
        }
    }
}