package rk.thermometer.ui.temperature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import rk.thermometer.domain.humidity.HumidityUseCase
import rk.thermometer.domain.temperature.TemperatureUseCase
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val temperatureUseCase: TemperatureUseCase,
    private val humidityUseCase: HumidityUseCase
) : ViewModel() {
    val tempFlow = temperatureUseCase.getTemperature().stateIn(
            scope = viewModelScope,
            initialValue = "0",
            started = SharingStarted.WhileSubscribed(5000)
        )
    val humFlow = humidityUseCase.getHumidity().stateIn(
            scope = viewModelScope,
            initialValue = "0",
            started = SharingStarted.WhileSubscribed(5000)
        )
}