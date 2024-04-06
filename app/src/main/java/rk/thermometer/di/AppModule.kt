package rk.thermometer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rk.thermometer.data.local.AppDao
import rk.thermometer.data.local.AppDatabase
import rk.thermometer.data.local.LocalDataSourceImpl
import rk.thermometer.data.network.NetworkDataSourceImpl
import rk.thermometer.data.repository.HumidityRepositoryImpl
import rk.thermometer.data.repository.LocalDataSource
import rk.thermometer.data.repository.NetworkDataSource
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
    fun provideAppDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.getAppDao()
    }

    @Provides
    fun provideLocalDataSource(dhtSensorDao: AppDao): LocalDataSource {
        return LocalDataSourceImpl(dhtSensorDao)
    }

    @Provides
    fun provideNetworkDataSource(): NetworkDataSource {
        return NetworkDataSourceImpl()
    }

    @Provides
    fun provideTempRepo(
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ): TemperatureRepository {
        return TemperatureRepositoryImpl(localDataSource, networkDataSource)
    }

    @Provides
    fun provideHumRepo(
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ): HumidityRepository {
        return HumidityRepositoryImpl(localDataSource, networkDataSource)
    }

}