package me.lucky.wyfy

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission

class ControlReceiver : BroadcastReceiver() {
    companion object {
        private const val SET_ON = "me.lucky.wyfy.action.SET_ON"
        private const val SET_OFF = "me.lucky.wyfy.action.SET_OFF"
        private const val KEY = "code"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        val enabled = when(intent.action) {
            SET_ON -> true
            SET_OFF -> false
            else -> return
        }
        val prefs = Preferences(context)
        if (prefs.isCodeEnabled) {
            val code = prefs.code
            if (code == "" || code != intent.getStringExtra(KEY)) return
        }
        try {
            setWifiEnabled(context, enabled)
        } catch (exc: SecurityException) {}
    }

    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    private fun setWifiEnabled(ctx: Context, enabled: Boolean): Boolean {
        @Suppress("deprecation")
        return ctx
            .applicationContext
            .getSystemService(WifiManager::class.java)
            ?.setWifiEnabled(enabled) == true
    }
}
