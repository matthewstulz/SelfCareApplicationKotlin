package com.github.stulzm2.selfcareapplicationkotlin.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal

@Dao
interface JournalDao {

    @Query("SELECT * from journal_table ORDER BY journal_entry ASC")
    fun getAllJournals(): LiveData<List<Journal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(journal: Journal)

    @Query("Delete FROM journal_table")
    fun deleteAll()
}