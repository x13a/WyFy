package me.lucky.wyfy

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission

class ControlReceiver : BroadcastReceiver() {
    companion object {
        private const val PREFIX = "me.lucky.wyfy.action"
        private const val SET_ON = "$PREFIX.SET_ON"
        private const val SET_OFF = "$PREFIX.SET_OFF"
        private const val TOGGLE = "$PREFIX.TOGGLE"
        private const val KEY = "code"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        val manager = Manager(context)
        val enabled = when(intent.action) {
            SET_ON -> true
            SET_OFF -> false
            TOGGLE -> !(manager.isWifiEnabled ?: return)
            else -> return
        }
        val prefs = Preferences(context)
        if (prefs.isCodeEnabled) {
            val code = prefs.code
            if (code == "" || code != intent.getStringExtra(KEY)) return
        }
        try {
            manager.setWifiEnabled(enabled)
        } catch (exc: SecurityException) {}
    }
    
    private class Manager(ctx: Context) {
        private val manager by lazy {
            ctx.applicationContext.getSystemService(WifiManager::class.java)
        }

        val isWifiEnabled = manager?.isWifiEnabled

        @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
        fun setWifiEnabled(enabled: Boolean): Boolean {
            @Suppress("deprecation")
            return manager?.setWifiEnabled(enabled) == true
        }
    }
}
