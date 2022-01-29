package me.lucky.wyfy

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class Preferences(ctx: Context) {
    companion object {
        private const val CODE_ENABLED = "code_enabled"
        private const val CODE = "code"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)

    var isCodeEnabled: Boolean
        get() = prefs.getBoolean(CODE_ENABLED, false)
        set(value) = prefs.edit { putBoolean(CODE_ENABLED, value) }

    var code: String
        get() = prefs.getString(CODE, "") ?: ""
        set(value) = prefs.edit { putString(CODE, value) }
}
