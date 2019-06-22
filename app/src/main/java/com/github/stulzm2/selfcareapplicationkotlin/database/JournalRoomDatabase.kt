package com.github.stulzm2.selfcareapplicationkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.github.stulzm2.selfcareapplicationkotlin.dao.JournalDao
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Journal::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class JournalRoomDatabase : RoomDatabase() {

    abstract fun journalDao(): JournalDao

    companion object {
        @Volatile
        private var INSTANCE: JournalRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): JournalRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JournalRoomDatabase::class.java,
                    "journal_database"
                ).fallbackToDestructiveMigration().addCallback(JournalDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

        private class JournalDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.journalDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(journalDao: JournalDao) {
            journalDao.deleteAll()
            val date = Calendar.getInstance().time
            var journal = Journal("TEST", date)
            journalDao.insert(journal)
            journal = Journal("TEST2", date)
            journalDao.insert(journal)
        }
    }
}