package com.snackbar.snackswipe

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun rememberSnackSwipeHostState(): SnackSwipeHostState = remember { SnackSwipeHostState() }

@Stable
class SnackSwipeHostState internal constructor() {
    private val _state = MutableStateFlow<SnackSwipeState>(SnackSwipeState.Hidden)
    internal val state: StateFlow<SnackSwipeState> = _state.asStateFlow()

    fun show(data: SnackSwipeData) {
        when (data.behavior.policy) {
            SnackPolicy.Replace -> _state.value = SnackSwipeState.Visible(data)
            SnackPolicy.Ignore -> if (_state.value is SnackSwipeState.Hidden) {
                _state.value = SnackSwipeState.Visible(data)
            }
            SnackPolicy.Enqueue -> _state.value = SnackSwipeState.Visible(data)
        }
    }

    fun dismissCurrent() {
        _state.value = SnackSwipeState.Hidden
    }
}

class SnackSwipeController internal constructor(
    private val hostState: SnackSwipeHostState
) {
    fun show(
        messageText: @Composable () -> Unit,
        icon: (@Composable (() -> Unit))? = null,
        customAction: (@Composable (() -> Unit))? = null,
        dismissAction: (@Composable (() -> Unit))? = null,
        backgroundColor: Color = Color.DarkGray,
        durationMillis: Long = 3000,
        shape: Shape = RoundedCornerShape(12.dp),
        elevation: Dp = 6.dp,
        innerPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
        outerPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
        behavior: SnackBehavior = SnackBehavior(durationMillis = durationMillis)
    ) {
        hostState.show(
            SnackSwipeData(
                messageText = messageText,
                icon = icon,
                customAction = customAction,
                dismissAction = dismissAction,
                backgroundColor = backgroundColor,
                shape = shape,
                elevation = elevation,
                innerPadding = innerPadding,
                outerPadding = outerPadding,
                behavior = behavior
            )
        )
    }

    fun close() {
        hostState.dismissCurrent()
    }
}

@Stable
data class SnackSwipeData(
    val messageText: @Composable () -> Unit,
    val icon: (@Composable (() -> Unit))?,
    val customAction: (@Composable (() -> Unit))?,
    val dismissAction: (@Composable (() -> Unit))?,
    val backgroundColor: Color,
    val shape: Shape,
    val elevation: Dp,
    val innerPadding: PaddingValues,
    val outerPadding: PaddingValues,
    val behavior: SnackBehavior
)
