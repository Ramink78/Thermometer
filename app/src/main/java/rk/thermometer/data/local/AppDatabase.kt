package rk.thermometer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalHumidity::class, LocalTemperature::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
}