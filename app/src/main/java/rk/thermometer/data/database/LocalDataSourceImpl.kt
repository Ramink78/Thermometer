package rk.thermometer.data.database

import kotlinx.coroutines.flow.Flow
import rk.thermometer.data.repository.LocalDataSource

class LocalDataSourceImpl(private val dhtSensorDao: DHTSensorDao) : LocalDataSource {
    override fun getHumidity(): Flow<String> {
        return dhtSensorDao.getHumidity()
    }

    override fun getTemperature(): Flow<String> {
        return dhtSensorDao.getTemperature()
    }
}