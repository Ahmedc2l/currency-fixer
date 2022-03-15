package com.ahmedc2l.currencyfixer.data.models

import android.util.Log
import com.ahmedc2l.currencyfixer.data.utils.SharedPreferencesUtils
import com.ahmedc2l.currencyfixer.domain.entities.Country
import com.ahmedc2l.currencyfixer.domain.entities.LatestExchangeRates
import com.google.gson.Gson

data class LatestExchangeRatesModel(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
) {
    companion object {
        private const val defaultRates = "{\n" +
                "      \"success\":true,\n" +
                "      \"timestamp\":1647239583,\n" +
                "      \"base\":\"EUR\",\n" +
                "      \"date\":\"2022-03-14\",\n" +
                "      \"rates\":{\n" +
                "        \"AED\":4.010821,\n" +
                "        \"AFN\":97.477727,\n" +
                "        \"ALL\":123.45245,\n" +
                "        \"AMD\":568.27814,\n" +
                "        \"ANG\":1.984861,\n" +
                "        \"AOA\":513.117376,\n" +
                "        \"ARS\":118.75732,\n" +
                "        \"AUD\":1.507182,\n" +
                "        \"AWG\":1.965771,\n" +
                "        \"AZN\":1.856396,\n" +
                "        \"BAM\":1.95615,\n" +
                "        \"BBD\":2.223604,\n" +
                "        \"BDT\":94.7762,\n" +
                "        \"BGN\":1.952079,\n" +
                "        \"BHD\":0.411375,\n" +
                "        \"BIF\":2257.670568,\n" +
                "        \"BMD\":1.091944,\n" +
                "        \"BND\":1.497472,\n" +
                "        \"BOB\":7.58234,\n" +
                "        \"BRL\":5.540903,\n" +
                "        \"BSD\":1.1013,\n" +
                "        \"BTC\":2.8225293e-5,\n" +
                "        \"BTN\":84.088422,\n" +
                "        \"BWP\":12.753919,\n" +
                "        \"BYN\":3.623859,\n" +
                "        \"BYR\":21402.094075,\n" +
                "        \"BZD\":2.219904,\n" +
                "        \"CAD\":1.395515,\n" +
                "        \"CDF\":2199.723472,\n" +
                "        \"CHF\":1.021535,\n" +
                "        \"CLF\":0.031894,\n" +
                "        \"CLP\":880.06251,\n" +
                "        \"CNY\":6.938107,\n" +
                "        \"COP\":4171.235377,\n" +
                "        \"CRC\":715.090043,\n" +
                "        \"CUC\":1.091944,\n" +
                "        \"CUP\":28.936505,\n" +
                "        \"CVE\":110.284046,\n" +
                "        \"CZK\":25.154344,\n" +
                "        \"DJF\":196.055654,\n" +
                "        \"DKK\":7.439793,\n" +
                "        \"DOP\":60.328971,\n" +
                "        \"DZD\":156.215409,\n" +
                "        \"EGP\":17.143188,\n" +
                "        \"ERN\":16.379159,\n" +
                "        \"ETB\":56.498775,\n" +
                "        \"EUR\":1,\n" +
                "        \"FJD\":2.303232,\n" +
                "        \"FKP\":0.837476,\n" +
                "        \"GBP\":0.838564,\n" +
                "        \"GEL\":3.516239,\n" +
                "        \"GGP\":0.837476,\n" +
                "        \"GHS\":7.819079,\n" +
                "        \"GIP\":0.837476,\n" +
                "        \"GMD\":58.337121,\n" +
                "        \"GNF\":9856.682753,\n" +
                "        \"GTQ\":8.488166,\n" +
                "        \"GYD\":230.407681,\n" +
                "        \"HKD\":8.549318,\n" +
                "        \"HNL\":27.117932,\n" +
                "        \"HRK\":7.574264,\n" +
                "        \"HTG\":117.385347,\n" +
                "        \"HUF\":381.51416,\n" +
                "        \"IDR\":15650.281292,\n" +
                "        \"ILS\":3.558409,\n" +
                "        \"IMP\":0.837476,\n" +
                "        \"INR\":83.612846,\n" +
                "        \"IQD\":1607.332861,\n" +
                "        \"IRR\":46189.213097,\n" +
                "        \"ISK\":144.91186,\n" +
                "        \"JEP\":0.837476,\n" +
                "        \"JMD\":168.768691,\n" +
                "        \"JOD\":0.774226,\n" +
                "        \"JPY\":128.617886,\n" +
                "        \"KES\":124.706605,\n" +
                "        \"KGS\":114.629179,\n" +
                "        \"KHR\":4466.91233,\n" +
                "        \"KMF\":492.618911,\n" +
                "        \"KPW\":982.7496,\n" +
                "        \"KRW\":1355.937275,\n" +
                "        \"KWD\":0.3317,\n" +
                "        \"KYD\":0.917758,\n" +
                "        \"KZT\":578.314577,\n" +
                "        \"LAK\":12643.999373,\n" +
                "        \"LBP\":1665.202825,\n" +
                "        \"LKR\":280.824054,\n" +
                "        \"LRD\":167.995615,\n" +
                "        \"LSL\":16.466505,\n" +
                "        \"LTL\":3.224226,\n" +
                "        \"LVL\":0.660506,\n" +
                "        \"LYD\":5.105528,\n" +
                "        \"MAD\":10.669745,\n" +
                "        \"MDL\":20.263885,\n" +
                "        \"MGA\":4454.76012,\n" +
                "        \"MKD\":61.640081,\n" +
                "        \"MMK\":1958.277422,\n" +
                "        \"MNT\":3143.457454,\n" +
                "        \"MOP\":8.877452,\n" +
                "        \"MRO\":389.823669,\n" +
                "        \"MUR\":47.772361,\n" +
                "        \"MVR\":16.870655,\n" +
                "        \"MWK\":887.373373,\n" +
                "        \"MXN\":22.88332,\n" +
                "        \"MYR\":4.59383,\n" +
                "        \"MZN\":69.69857,\n" +
                "        \"NAD\":16.466427,\n" +
                "        \"NGN\":454.073256,\n" +
                "        \"NIO\":39.388163,\n" +
                "        \"NOK\":9.848376,\n" +
                "        \"NPR\":134.542467,\n" +
                "        \"NZD\":1.609694,\n" +
                "        \"OMR\":0.420166,\n" +
                "        \"PAB\":1.10128,\n" +
                "        \"PEN\":4.086268,\n" +
                "        \"PGK\":3.881306,\n" +
                "        \"PHP\":57.239135,\n" +
                "        \"PKR\":197.435605,\n" +
                "        \"PLN\":4.787464,\n" +
                "        \"PYG\":7678.896445,\n" +
                "        \"QAR\":3.975761,\n" +
                "        \"RON\":4.949788,\n" +
                "        \"RSD\":117.6744,\n" +
                "        \"RUB\":143.860835,\n" +
                "        \"RWF\":1117.833283,\n" +
                "        \"SAR\":4.096422,\n" +
                "        \"SBD\":8.788348,\n" +
                "        \"SCR\":15.730861,\n" +
                "        \"SDG\":488.098425,\n" +
                "        \"SEK\":10.633101,\n" +
                "        \"SGD\":1.490738,\n" +
                "        \"SHP\":1.504042,\n" +
                "        \"SLL\":12792.119112,\n" +
                "        \"SOS\":637.695315,\n" +
                "        \"SRD\":22.471109,\n" +
                "        \"STD\":22601.027382,\n" +
                "        \"SVC\":9.636576,\n" +
                "        \"SYP\":2742.96263,\n" +
                "        \"SZL\":16.531906,\n" +
                "        \"THB\":36.513526,\n" +
                "        \"TJS\":14.377383,\n" +
                "        \"TMT\":3.821803,\n" +
                "        \"TND\":3.212463,\n" +
                "        \"TOP\":2.480348,\n" +
                "        \"TRY\":16.255655,\n" +
                "        \"TTD\":7.480023,\n" +
                "        \"TWD\":31.125302,\n" +
                "        \"TZS\":2527.849559,\n" +
                "        \"UAH\":32.378188,\n" +
                "        \"UGX\":3981.151073,\n" +
                "        \"USD\":1.091944,\n" +
                "        \"UYU\":46.871094,\n" +
                "        \"UZS\":12004.233159,\n" +
                "        \"VEF\":233490537473.54178,\n" +
                "        \"VND\":24999.502183,\n" +
                "        \"VUV\":124.608893,\n" +
                "        \"WST\":2.863126,\n" +
                "        \"XAF\":656.076311,\n" +
                "        \"XAG\":0.042664,\n" +
                "        \"XAU\":0.000553,\n" +
                "        \"XCD\":2.951033,\n" +
                "        \"XDR\":0.794544,\n" +
                "        \"XOF\":656.076311,\n" +
                "        \"XPF\":119.513309,\n" +
                "        \"YER\":273.258813,\n" +
                "        \"ZAR\":16.470702,\n" +
                "        \"ZMK\":9828.834278,\n" +
                "        \"ZMW\":20.098755,\n" +
                "        \"ZWL\":351.605386\n" +
                "      }" +
                "    }"

        fun saveToSharedPreferences(jsonString: String) {
            Log.i("TAG", "saveToSharedPreferences: $jsonString")
            SharedPreferencesUtils.saveString("_latest_rates", jsonString)
        }

        fun getLastSaved(): LatestExchangeRatesModel {
            val gson = Gson()
            return gson.fromJson(
                SharedPreferencesUtils.getString("_latest_rates", defaultRates),
                LatestExchangeRatesModel::class.java
            )
        }
    }
}

fun LatestExchangeRatesModel.toDomainEntity(): LatestExchangeRates =
    LatestExchangeRates(this.date, this.rates.toDomainCountries())

fun Map<String, Double>.toDomainCountries(): List<Country> =
    this.map { Country(it.key, it.value) }