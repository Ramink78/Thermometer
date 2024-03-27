package rk.thermometer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rk.thermometer.data.database.AppDatabase
import rk.thermometer.data.database.DHTSensorDao
import rk.thermometer.data.database.LocalDataSourceImpl
import rk.thermometer.data.repository.HumidityRepositoryImpl
import rk.thermometer.data.repository.LocalDataSource
import rk.thermometer.data.repository.TemperatureRepositoryImpl
import rk.thermometer.domain.humidity.HumidityRepository
import rk.thermometer.domain.temperature.TemperatureRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "Thermometer-DB")
            .build()
    }

    @Provides
    fun provideDHTSensorDao(appDatabase: AppDatabase): DHTSensorDao {
        return appDatabase.getDHTSensorDao()
    }

    @Provides
    fun provideLocalDataSource(dhtSensorDao: DHTSensorDao): LocalDataSource {
        return LocalDataSourceImpl(dhtSensorDao)
    }

    @Provides
    fun provideTempRepo(localDataSource: LocalDataSource): TemperatureRepository {
        return TemperatureRepositoryImpl(localDataSource)
    }

    @Provides
    fun provideHumRepo(localDataSource: LocalDataSource): HumidityRepository {
        return HumidityRepositoryImpl(localDataSource)
    }

}