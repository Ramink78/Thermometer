package rk.thermometer.data.network.models

import kotlinx.serialization.Serializable


@Serializable
data class NetworkHumidity(
    val value: String
)