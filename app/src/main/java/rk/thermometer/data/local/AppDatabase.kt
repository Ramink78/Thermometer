package rk.thermometer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import rk.thermometer.domain.entity.DHTSensor

@Database(entities = [DHTSensor::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDHTSensorDao(): DHTSensorDao
}