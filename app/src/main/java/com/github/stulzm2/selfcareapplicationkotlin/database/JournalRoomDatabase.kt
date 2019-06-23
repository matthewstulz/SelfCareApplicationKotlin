package com.github.stulzm2.selfcareapplicationkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.github.stulzm2.selfcareapplicationkotlin.R
import com.github.stulzm2.selfcareapplicationkotlin.dao.JournalDao
import com.github.stulzm2.selfcareapplicationkotlin.model.Journal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Journal::class], version = 1, exportSchema = false)
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
//            journalDao.deleteAll()
            val date = Calendar.getInstance().time
            val journal = Journal("Welcome to the Self Care Journal. Simply swipe left or right " +
                    "on an entry to delete. Click on an entry to view or edit the post. All entries are " +
                    "stored locally on your phone so every entry is safe and sound.", date)
            journalDao.insert(journal)
        }
    }
}