package me.lucky.wyfy

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.*

import me.lucky.wyfy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: Preferences
    private var clipboardManager: ClipboardManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setup()
    }

    private fun init() {
        prefs = Preferences(this)
        clipboardManager = getSystemService(ClipboardManager::class.java)
        if (prefs.authCode.isEmpty()) prefs.authCode = makeCode()
        updateAuthCodeColorState()
        binding.apply {
            authCode.text = prefs.authCode
            toggle.isChecked = getComponentState(ControlReceiver::class.java)
        }
    }

    private fun setup() {
        binding.apply {
            authCode.setOnClickListener {
                clipboardManager?.setPrimaryClip(ClipData.newPlainText("", prefs.authCode))
                if (clipboardManager != null)
                    Snackbar.make(authCode, R.string.copied_popup, Snackbar.LENGTH_SHORT).show()
            }
            authCode.setOnLongClickListener {
                prefs.isAuthCodeEnabled = !prefs.isAuthCodeEnabled
                updateAuthCodeColorState()
                true
            }
            toggle.setOnCheckedChangeListener { _, isChecked ->
                setComponentState(ControlReceiver::class.java, isChecked)
            }
        }
    }

    private fun updateAuthCodeColorState() {
        binding.authCode.setBackgroundColor(getColor(
            if (prefs.isAuthCodeEnabled) R.color.code_on else R.color.code_off
        ))
    }

    private fun makeCode() = UUID.randomUUID().toString()

    private fun setComponentState(cls: Class<*>, value: Boolean) {
        packageManager.setComponentEnabledSetting(
            ComponentName(this, cls),
            if (value) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP,
        )
    }

    private fun getComponentState(cls: Class<*>): Boolean {
        return packageManager.getComponentEnabledSetting(ComponentName(this, cls)) ==
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    }
}
