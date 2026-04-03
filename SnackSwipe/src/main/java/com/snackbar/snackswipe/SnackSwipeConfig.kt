package com.snackbar.snackswipe

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.core.spring

enum class SnackPolicy {
    Replace,
    Ignore,
    Enqueue
}

enum class SwipeDirection {
    Up,
    Left,
    Right
}

data class SwipeConfig(
    val enabled: Boolean = true,
    val allowedDirections: Set<SwipeDirection> = setOf(
        SwipeDirection.Up,
        SwipeDirection.Left,
        SwipeDirection.Right
    ),
    val thresholdFraction: Float = 0.05f
)

data class AnimationConfig(
    val enter: EnterTransition =
        slideInVertically(animationSpec = spring(), initialOffsetY = { -it }) + fadeIn(),
    val exit: ExitTransition =
        slideOutVertically(animationSpec = spring(), targetOffsetY = { -it }) + fadeOut(),
    val swipeDismissExit: ExitTransition = ExitTransition.None
)

data class SnackBehavior(
    val durationMillis: Long = 3000,
    val policy: SnackPolicy = SnackPolicy.Replace,
    val swipe: SwipeConfig = SwipeConfig(),
    val animation: AnimationConfig = AnimationConfig()
)
