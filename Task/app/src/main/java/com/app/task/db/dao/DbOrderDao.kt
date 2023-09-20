package com.app.task.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.app.task.db.entity.DbOrder

@Dao
interface DbOrderDao {

    @Insert(onConflict = REPLACE)
    fun insertOne(vararg order: DbOrder)

    @Query("DELETE FROM DbOrder")
    fun clearData()
}