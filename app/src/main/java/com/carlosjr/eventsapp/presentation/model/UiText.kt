package com.carlosjr.eventsapp.presentation.model

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {

    data class DynamicString(val value: String) : UiText()
    class StringResource(@StringRes val idResource: Int, vararg val args: Any): UiText()

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(idResource, *args)
        }
    }

    companion object {

        fun Int.resourceToUiText(): UiText {
            return StringResource(this)
        }

        fun String.stringToUiText(): UiText {
            return DynamicString(this)
        }
    }
}
