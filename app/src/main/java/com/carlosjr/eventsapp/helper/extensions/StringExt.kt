package com.carlosjr.eventsapp.helper.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun String.convertHttpToHttps() : String {
    return if (this.contains(HTTPS)) this else this.replace(HTTP, HTTPS)
}

fun Double.monetaryFormat(): String {
    val decimalFormat: DecimalFormat =
        NumberFormat.getCurrencyInstance(Locale("pt", "BR")) as DecimalFormat
    return decimalFormat.format(BigDecimal(this)).replace("R$", "R$ ")
}

private const val HTTP = "http"
private const val HTTPS = "https"