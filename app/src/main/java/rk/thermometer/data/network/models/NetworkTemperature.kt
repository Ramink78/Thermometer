package rk.thermometer.data.network.models

import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkTemperature(
    val value: String
)