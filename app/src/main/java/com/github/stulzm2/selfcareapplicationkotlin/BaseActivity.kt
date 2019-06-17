package com.github.stulzm2.selfcareapplicationkotlin

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class BaseActivity : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themePref = sharedPreferences.getString("themePref", ThemeHelper.DEFAULT_MODE);
        ThemeHelper.applyTheme(themePref)
    }
}