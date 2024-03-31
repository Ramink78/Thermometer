package rk.thermometer.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.ToasterDefaults
import com.dokar.sonner.rememberToasterState
import rk.thermometer.R
import kotlin.time.Duration

const val loadingToastId = "loadingId"
const val successToastId = "successId"

@Composable
fun SuccessToast(
    message: String
) {
    val state = rememberToasterState()
    state.show(
        message = message,
        duration = ToasterDefaults.DurationShort,
        type = ToastType.Success,
        id = successToastId
    )
    Toaster(
        state = state,
        darkTheme = isSystemInDarkTheme(),
        alignment = Alignment.TopCenter,
        shape = { CircleShape },
        swipeable = true,
        richColors = true,
    )

}

@Composable
fun LoadingToast() {
    val state = rememberToasterState()
    state.show(
        message = stringResource(R.string.loading),
        duration = Duration.INFINITE,
        type = ToastType.Info,
        id = loadingToastId
    )
    Toaster(
        state = state,
        darkTheme = isSystemInDarkTheme(),
        alignment = Alignment.TopCenter,
        shape = { CircleShape },
        swipeable = false,
        richColors = true,
        iconSlot = {
            Box(modifier = Modifier.padding(end = 16.dp)) {
                CircularProgressIndicator(
                    strokeWidth = 1.dp,
                    modifier = Modifier.size(20.dp),
                    color = Color(0xff5896f3)
                )
            }

        }
    )

}

