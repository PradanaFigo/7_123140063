package com.example.project.platform

import android.content.Context
import android.os.Build
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class DeviceInfo : KoinComponent {

    private val context: Context by inject()

    actual fun getDeviceName(): String = Build.MODEL

    actual fun getOsVersion(): String = "Android ${Build.VERSION.RELEASE}"

    actual fun getAppVersion(): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "1.0.0"
        } catch (e: Exception) {
            "1.0.0"
        }
    }
}