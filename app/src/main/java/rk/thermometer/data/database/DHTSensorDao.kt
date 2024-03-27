package rk.thermometer.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DHTSensorDao {
    @Query("select temperature from dht_sensor")
    fun getTemperature(): Flow<String>

    @Query("select  humidity from dht_sensor")
    fun getHumidity(): Flow<String>
}