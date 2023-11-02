package com.app.qfonapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.qfonapp.util.Config.DB_QFONAPP
import com.app.qfonapp.util.Config.DB_VERSION

@Database(
    entities = [DbCreatePoll::class, DbPollOption::class],
    version = DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun createPollDao(): DbCreatePollDao
    abstract fun addPollOptionDao(): DbPollOptionDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DB_QFONAPP
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}