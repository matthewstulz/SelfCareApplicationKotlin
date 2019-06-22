package com.github.stulzm2.selfcareapplicationkotlin.model

import androidx.room.*
import com.github.stulzm2.selfcareapplicationkotlin.database.DateConverter
import java.util.*
import androidx.room.TypeConverters
import androidx.room.PrimaryKey


@Entity(tableName = "journal_table")
data class Journal(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "journal_entry") val entry: String,
    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "journal_date") val date: Date
)
{
    constructor(entry: String = "", date: Date = Date()) : this(0, entry, date)
}