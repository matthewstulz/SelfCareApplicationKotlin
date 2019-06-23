package com.github.stulzm2.selfcareapplicationkotlin.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.github.stulzm2.selfcareapplicationkotlin.dao.JournalDao
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal

class JournalRepository(private val journalDao: JournalDao) {

    val allJournals: LiveData<List<Journal>> = journalDao.getAllJournals()

    @WorkerThread
    suspend fun insert(journal: Journal) {
        journalDao.insert(journal)
    }

    @WorkerThread
    suspend fun update(journal: Journal) {
        journalDao.update(journal)
    }
}