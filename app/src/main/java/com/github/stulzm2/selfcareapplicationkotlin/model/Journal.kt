package com.github.stulzm2.selfcareapplicationkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "journal_table")
class Journal(@PrimaryKey @ColumnInfo(name = "entry") val entry: String, val date: Date)