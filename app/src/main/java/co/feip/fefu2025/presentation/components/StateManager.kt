package co.feip.fefu2025.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.feip.fefu2025.R
import co.feip.fefu2025.common.BaseState

@Composable
fun StateManager(
    circularProgressIndicatorModifier: Modifier = Modifier,
    errorTextModifier: Modifier = Modifier,
    state: BaseState,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    when {
        state.isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = circularProgressIndicatorModifier
                )
            }
        }

        state.error.isNotEmpty() -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = errorTextModifier,
                    text = stringResource(R.string.error_message, state.error)
                )

                Button(
                    onClick = onClick
                ) {
                    Text(
                        text = stringResource(R.string.try_again)
                    )
                }
            }
        }

        else -> {
            content()
        }
    }
}
