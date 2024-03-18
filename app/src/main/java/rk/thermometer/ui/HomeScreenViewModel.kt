package rk.thermometer.ui

import androidx.lifecycle.ViewModel
import rk.thermometer.domain.humidity.HumidityUseCase
import rk.thermometer.domain.temperature.TemperatureUseCase

class HomeScreenViewModel(
    private val temperatureUseCase: TemperatureUseCase,
    private val humidityUseCase: HumidityUseCase
) : ViewModel() {
}