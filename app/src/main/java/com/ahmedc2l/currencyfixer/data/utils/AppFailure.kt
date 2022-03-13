package com.ahmedc2l.currencyfixer.data.utils

import org.json.JSONObject

abstract class AppFailure {
    abstract fun toErrorString(): String
}

class SomethingWentWrongFailure(private val message: String?) : AppFailure() {
    override fun toErrorString(): String = message ?: "SomethingWentWrong"
}

class FixerNetworkFailure(private val message: String) : AppFailure() {
    override fun toErrorString(): String = message
}

/**
 * The fixer API failure responses is always return in the below format.
{
"success": false,
"error": {
"code": 104,
"info": "Your monthly API request volume has been reached. Please upgrade your plan."
}
}
 */
fun JSONObject.getErrorString(): String = try {
    val error = this.getJSONObject("error")
    "${error.getInt("code")}: ${error.getString("info")}"
} catch (e: Exception) {
    e.printStackTrace()
    e.message ?: "Something went wrong!"
}
