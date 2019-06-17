package com.github.stulzm2.selfcareapplicationkotlin

import androidx.appcompat.app.AppCompatDelegate

class ThemeHelper {

    companion object {

        const val LIGHT_MODE = "light"
        const val DARK_MODE = "dark"
        const val DEFAULT_MODE = "default"

        fun applyTheme(themePref: String?) {
            when (themePref) {
                LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                DEFAULT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            }
        }
    }
}