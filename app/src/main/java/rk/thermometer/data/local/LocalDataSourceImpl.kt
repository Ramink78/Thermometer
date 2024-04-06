package rk.thermometer.data.local

import kotlinx.coroutines.flow.Flow
import rk.thermometer.data.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: AppDao) :
    LocalDataSource {
    override fun getHumidity(): Flow<List<LocalHumidity>> {
        return dao.getHumidity()
    }

    override fun getTemperature(): Flow<List<LocalTemperature>> {
        return dao.getTemperature()
    }

    override suspend fun updateTemperature(localTemperature: LocalTemperature) {
        dao.addNewTemperature(localTemperature)
    }

    override suspend fun updateHumidity(localHumidity: LocalHumidity) {
        dao.addNewHumidity(localHumidity)
    }
}