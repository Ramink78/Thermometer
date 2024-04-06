package rk.thermometer.data.network.models

import kotlinx.serialization.Serializable
import rk.thermometer.data.local.LocalHumidity
import rk.thermometer.data.local.LocalTemperature

@Serializable
data class NetworkHumidity(
    val value: String
)

@Serializable
data class NetworkTemperature(
    val value: String
)
fun NetworkHumidity.toLocal()=
    LocalHumidity(value=value, lastUpdate = "")
fun NetworkTemperature.toLocal()=
    LocalTemperature(value = value, lastUpdate = "")