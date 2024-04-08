package rk.thermometer.data.network

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.PrimaryKey
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresListDataFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import rk.thermometer.data.network.models.NetworkHumidity
import rk.thermometer.data.network.models.NetworkTemperature
import rk.thermometer.data.repository.NetworkDataSource

class NetworkDataSourceImpl : NetworkDataSource {
    private val temperatureChannel = supabase.channel("temperatureChannel")
    private val humidityChannel = supabase.channel("humidityChannel")

    @OptIn(SupabaseExperimental::class)
    override suspend fun getTemperatureFlow(): Flow<NetworkTemperature> {
        val temperatureChangeFlow =
            temperatureChannel.postgresListDataFlow<NetworkTemperature>(
                table = "temperature",
                primaryKey = PrimaryKey("id") { it.id.toString() }
            ).map { it.last() }

        temperatureChannel.subscribe(true)
        return temperatureChangeFlow

    }

    @OptIn(SupabaseExperimental::class)
    override suspend fun getHumidityFlow(): Flow<NetworkHumidity> {
        val humidityChangeFlow =
            humidityChannel.postgresListDataFlow<NetworkHumidity>(
                table = "humidity",
                primaryKey = PrimaryKey("id") { it.id.toString() }).map { it.last() }
        humidityChannel.subscribe(true)
        return humidityChangeFlow
    }
}