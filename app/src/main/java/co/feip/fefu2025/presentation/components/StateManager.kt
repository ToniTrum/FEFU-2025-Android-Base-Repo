package co.feip.fefu2025.presentation.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.feip.fefu2025.R
import co.feip.fefu2025.common.BaseState

@Composable
fun StateManager(
    circularProgressIndicatorModifier: Modifier = Modifier,
    errorTextModifier: Modifier = Modifier,
    state: BaseState,
    content: @Composable () -> Unit
) {
    when {
        state.isLoading -> {
            CircularProgressIndicator(
                modifier = circularProgressIndicatorModifier
            )
        }
        state.error.isNotEmpty() -> {
            Text(
                modifier = errorTextModifier,
                text = stringResource(R.string.error_message, state.error)
            )
        }
        else -> {
            content()
        }
    }
}
