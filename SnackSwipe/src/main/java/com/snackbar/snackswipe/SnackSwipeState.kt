package com.snackbar.snackswipe

internal sealed interface SnackSwipeState {
    data object Hidden : SnackSwipeState
    data class Visible(val data: SnackSwipeData) : SnackSwipeState
}
