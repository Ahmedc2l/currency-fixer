package com.ahmedc2l.currencyfixer.app.main

interface MainActivityEventsListener {
    fun showLoading()
    fun hideLoading()
    fun showErrorMessage(message: String)
}