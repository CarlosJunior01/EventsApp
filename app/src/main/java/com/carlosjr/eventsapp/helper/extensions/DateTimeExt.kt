package com.carlosjr.eventsapp.helper.extensions

import android.text.format.DateFormat
import java.util.Calendar
import java.util.TimeZone

fun formatDate(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp * 1000L
    return DateFormat.format(DATE_FORMAT_PATTERN, calendar).toString()
}

fun formatDateDay(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp * 1000L
    return DateFormat.format(DAY_FORMAT_PATTERN, calendar).toString()
}

fun formatDateMonth(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp * 1000L
    return DateFormat.format(MONTH_FORMAT_PATTERN, calendar).toString()
}

fun formatTime(timestamp: Long): String {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeZone = TimeZone.getTimeZone(TIMEZONE_GMT_03)
    calendar.timeInMillis = timestamp * 1000L
    return DateFormat.format(TIME_FORMAT_PATTERN, calendar).toString()
}

private const val TIMEZONE_GMT_03 = "GMT-03"
private const val TIME_FORMAT_PATTERN = "HH'h'mm"
private const val DAY_FORMAT_PATTERN = "dd"
private const val MONTH_FORMAT_PATTERN = "MMM"
private const val DATE_FORMAT_PATTERN = "dd 'de' MMM 'de' yyyy"
