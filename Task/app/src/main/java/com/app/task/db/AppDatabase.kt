package com.app.task.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.task.db.dao.DbOrderDao
import com.app.task.db.entity.DbOrder
import com.app.task.utils.Configs

@Database(
    entities = [
        DbOrder::class
    ],
    version = Configs.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao(): DbOrderDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, Configs.DB_ORDER
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}