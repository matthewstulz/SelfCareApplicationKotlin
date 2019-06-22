package com.github.stulzm2.selfcareapplicationkotlin.database

import androidx.room.TypeConverter

import java.text.DateFormat
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return (date?.time)?.toLong()
    }

    companion object {

        fun getDateFormat(): DateFormat {
            return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault())
        }
    }

}