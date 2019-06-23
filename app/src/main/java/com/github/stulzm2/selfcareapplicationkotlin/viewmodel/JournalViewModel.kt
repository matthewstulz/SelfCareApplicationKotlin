package com.github.stulzm2.selfcareapplicationkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.github.stulzm2.selfcareapplicationkotlin.database.JournalRoomDatabase
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal
import com.github.stulzm2.selfcareapplicationkotlin.repository.JournalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JournalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JournalRepository
    val allJournals: LiveData<List<Journal>>

    init {
        val journalsDao = JournalRoomDatabase.getDatabase(application, viewModelScope).journalDao()
        repository = JournalRepository(journalsDao)
        allJournals = repository.allJournals
    }

    fun insert(journal: Journal) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(journal)
    }

    fun update(journal: Journal) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(journal)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}