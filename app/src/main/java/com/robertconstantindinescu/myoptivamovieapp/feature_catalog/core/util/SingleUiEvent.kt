package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util

sealed class SingleUiEvent{
    object NavigationSuccess: SingleUiEvent()
    object NavigateUp: SingleUiEvent()
    data class ShowSnackBar(val message: UiText): SingleUiEvent()
}
