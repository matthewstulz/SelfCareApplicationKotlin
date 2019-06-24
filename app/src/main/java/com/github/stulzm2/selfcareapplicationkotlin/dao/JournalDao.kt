package com.github.stulzm2.selfcareapplicationkotlin.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal

@Dao
interface JournalDao {

    @Query("SELECT * from journal_table ORDER BY id ASC")
    fun getAllJournals(): LiveData<List<Journal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(journal: Journal)

    @Update
    suspend fun update(journal: Journal)

    @Query("Delete FROM journal_table")
    fun deleteAll()

    @Delete
    suspend fun delete(journal: Journal)
}