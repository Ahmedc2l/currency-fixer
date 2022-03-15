package com.ahmedc2l.currencyfixer.data.utils

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.ahmedc2l.currencyfixer.app.main.CurrencyFixerApplication

object SharedPreferencesUtils {
    private val sharedPref: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(CurrencyFixerApplication.instance)
    }

    private val sharedPrefEditor: SharedPreferences.Editor by lazy {
        sharedPref.edit()
    }

    fun saveString(key: String, value: String) = sharedPrefEditor.putString(key, value).apply()
    fun getString(key: String, defaultValue: String?) = sharedPref.getString(key, defaultValue) ?: ""
}