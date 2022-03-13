package com.ahmedc2l.currencyfixer.data.utils

abstract class AppFailure{
    abstract fun toErrorString(): String
}

class SomethingWentWrongFailure(private val message: String?): AppFailure(){
    override fun toErrorString(): String = message ?: "SomethingWentWrong"
}