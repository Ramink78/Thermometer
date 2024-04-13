package rk.thermometer.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rk.thermometer.data.local.LocalHumidity
import rk.thermometer.data.local.LocalTemperature

@Serializable
data class NetworkHumidity(
    @SerialName("id")
    val id:Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("percent")
    val percent: Float
)

@Serializable
data class NetworkTemperature(
    @SerialName("id")
    val id: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("celcius")
    val celcius: Float,
    @SerialName("fahrenheit")
    val fahrenheit: Float
)
fun NetworkHumidity.toLocal()=
    LocalHumidity(value = percent.toString(), lastUpdate = createdAt)
fun NetworkTemperature.toLocal()=
    LocalTemperature(value = celcius.toString(), lastUpdate = createdAt)