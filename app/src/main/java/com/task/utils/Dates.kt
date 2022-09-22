package com.task.utils

import java.text.SimpleDateFormat
import java.util.*


fun convertDateToUserFormat(
    longDate: String,
    dateFormat: String = DateConstants.DATE_VIEW
): String {
    val parser = SimpleDateFormat(DateConstants.API_DATE_FORMAT)
    //val formatter = SimpleDateFormat(dateFormat, Locale.US)
    val formatter = SimpleDateFormat(dateFormat, java.util.Locale.getDefault())
    return formatter.format(parser.parse(longDate))
}
fun convertDateToDayOfWeek(
    longDate: String,
    dateFormat: String = DateConstants.Day_VIEW
): String {
    val parser = SimpleDateFormat(DateConstants.API_DATE_FORMAT)
    //val formatter = SimpleDateFormat(dateFormat, Locale.US)
    val formatter = SimpleDateFormat(dateFormat, java.util.Locale.getDefault())
    return formatter.format(parser.parse(longDate))
}

fun convertDateToTime(
    longDate: String,
    dateFormat: String = DateConstants.TIME_VIEW
): String {
    val parser = SimpleDateFormat(DateConstants.API_DATE_FORMAT_WithTime)
    //val formatter = SimpleDateFormat(dateFormat, Locale.US)
    val formatter = SimpleDateFormat(dateFormat, java.util.Locale.getDefault())
    return formatter.format(parser.parse(longDate))
}
