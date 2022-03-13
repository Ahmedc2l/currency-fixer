package com.ahmedc2l.currencyfixer.app.main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurrencyFixerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: CurrencyFixerApplication
            private set // Only DevotanaApplication can set the instance value
    }
}