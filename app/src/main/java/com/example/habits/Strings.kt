package com.example.habits

import androidx.annotation.StringRes

object Strings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return App.instance.getString(stringRes, *formatArgs)
    }

    const val HABIT_CREATE = "HABIT_CREATE"
    const val HABIT_EDIT_POSITION = "HABIT_EDIT_POSITION"
    const val HABIT_CREATE_ACTIVITY_REQUEST_CODE = 0
    const val HABIT_EDIT_ACTIVITY_REQUEST_CODE = 1
}

