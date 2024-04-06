package rk.thermometer.ui.humidity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import rk.thermometer.data.model.Humidity
import rk.thermometer.domain.humidity.HumidityRepository
import rk.thermometer.domain.humidity.HumidityUseCase
import javax.inject.Inject

@HiltViewModel
class HumidityScreenViewModel @Inject constructor(
    private val humidityUseCase: HumidityUseCase,
    private val humidityRepository: HumidityRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            humidityRepository.sync()
        }
    }

    val humFlow = humidityUseCase.getHumidity()
        .map {
            if (it.isNotEmpty())
                it.last()
            else
                Humidity(value = "0", lastUpdate = "")
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = Humidity(value = "0", lastUpdate = ""),
            started = SharingStarted.WhileSubscribed(5000)
        )


}