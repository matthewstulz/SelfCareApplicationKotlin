package com.github.stulzm2.selfcareapplicationkotlin.model

import androidx.annotation.NonNull
import androidx.room.*
import com.github.stulzm2.selfcareapplicationkotlin.database.DateConverter
import java.util.*
import androidx.room.TypeConverters
import androidx.room.PrimaryKey

@Entity(tableName = "journal_table")
data class Journal(
    @NonNull @PrimaryKey(autoGenerate = true)
    var id: Int,
    var entry: String,
    @TypeConverters(DateConverter::class)
    var date: Date
)