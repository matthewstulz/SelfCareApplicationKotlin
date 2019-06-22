package com.github.stulzm2.selfcareapplicationkotlin.model

import androidx.room.*
import com.github.stulzm2.selfcareapplicationkotlin.database.DateConverter
import java.util.*

@Entity(tableName = "journal_table")
data class Journal(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "journal_entry") val entry: String,
    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "journal_date") val date: Date)

//@Entity(tableName = "journal_table")
//class Journal(@PrimaryKey @ColumnInfo(name = "journal") val journal: String)