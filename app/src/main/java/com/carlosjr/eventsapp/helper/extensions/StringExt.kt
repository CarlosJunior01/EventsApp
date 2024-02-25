package com.carlosjr.eventsapp.helper.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double.monetaryFormat(): String {
    val decimalFormat: DecimalFormat =
        NumberFormat.getCurrencyInstance(Locale("pt", "BR")) as DecimalFormat
    return decimalFormat.format(BigDecimal(this)).replace("R$", "R$ ")
}