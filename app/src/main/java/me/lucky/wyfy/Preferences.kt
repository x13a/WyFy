package me.lucky.wyfy

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class Preferences(ctx: Context) {
    companion object {
        private const val AUTH_CODE_ENABLED = "auth_code_enabled"
        private const val AUTH_CODE = "auth_code"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)

    var isAuthCodeEnabled: Boolean
        get() = prefs.getBoolean(AUTH_CODE_ENABLED, false)
        set(value) = prefs.edit { putBoolean(AUTH_CODE_ENABLED, value) }

    var authCode: String
        get() = prefs.getString(AUTH_CODE, "") ?: ""
        set(value) = prefs.edit { putString(AUTH_CODE, value) }
}
