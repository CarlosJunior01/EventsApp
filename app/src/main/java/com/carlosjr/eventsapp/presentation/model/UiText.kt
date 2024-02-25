package com.carlosjr.eventsapp.presentation.model

import androidx.annotation.StringRes

sealed class UiText {

    data class DynamicString(val value: String) : UiText()
    class StringResource(@StringRes val idResource: Int, vararg val args: Any): UiText()

    companion object {

        fun Int.resourceToUiText(): UiText {
            return StringResource(this)
        }
    }
}
