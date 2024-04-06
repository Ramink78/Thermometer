package rk.thermometer.ui.temperature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import rk.thermometer.data.model.Temperature
import rk.thermometer.domain.temperature.TemperatureRepository
import rk.thermometer.domain.temperature.TemperatureUseCase
import javax.inject.Inject

@HiltViewModel
class TemperatureScreenViewModel @Inject constructor(
    private val temperatureUseCase: TemperatureUseCase,
    private val temperatureRepository: TemperatureRepository,
) : ViewModel() {
    init {
        viewModelScope.launch {
            temperatureRepository.sync()
        }
    }

    val tempFlow = temperatureUseCase.getTemperature()
        .map {
            if (it.isNotEmpty())
                it.last()
            else
                Temperature(value = "0", lastUpdate = "")
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = Temperature(value = "0", lastUpdate = ""),
            started = SharingStarted.WhileSubscribed(5000)
        )

}