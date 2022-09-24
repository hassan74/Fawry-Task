package com.task.utils

import java.util.*

fun fourHourLonger(lastDateMillis: Long): Boolean {
    if(lastDateMillis==0L)
        return true
    val curretDate = Calendar.getInstance()
    val lastDate = Date(lastDateMillis)

    val millis: Long = curretDate.time.time - lastDate.time
    val hours = (millis / (1000 * 60 * 60)).toInt()
    val mins = (millis / (1000 * 60) % 60).toInt()

    if (hours >= 4)
        return true
    return false
}