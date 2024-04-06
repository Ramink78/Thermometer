package rk.thermometer.data.network

import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rk.thermometer.data.network.models.NetworkHumidity
import rk.thermometer.data.network.models.NetworkTemperature
import rk.thermometer.data.repository.NetworkDataSource

class NetworkDataSourceImpl : NetworkDataSource {
    private val temperatureChannel = supabase.channel("temperatureChannel")
    private val humidityChannel = supabase.channel("humidityChannel")
    override suspend fun getTemperatureFlow(): Flow<NetworkTemperature> {
        val temperatureChangeFlow =
            temperatureChannel.postgresChangeFlow<PostgresAction.Insert>(schema = "public") {
                table = temperatureTable
            }.map {
                NetworkTemperature(it.record["celcius"].toString())
            }
        temperatureChannel.subscribe(true)
        return temperatureChangeFlow

    }

    override suspend fun getHumidityFlow(): Flow<NetworkHumidity> {
        val humidityChangeFlow =
            humidityChannel.postgresChangeFlow<PostgresAction.Insert>(schema = "public") {
                table = humidityTable
            }.map {
                NetworkHumidity(it.record["percent"].toString())
            }
        humidityChannel.subscribe(true)
        return humidityChangeFlow
    }
}