package rk.thermometer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("select * from temperature")
    fun getTemperature(): Flow<List<LocalTemperature>>

    @Query("select * from humidity")
    fun getHumidity(): Flow<List<LocalHumidity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewTemperature(localTemperature: LocalTemperature)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewHumidity(localHumidity: LocalHumidity)
}