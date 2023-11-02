package com.app.qfonapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.qfonapp.util.Config.COL_CREATED_AT
import com.app.qfonapp.util.Config.COL_POLL_ATTEMPTED

@Dao
interface DbCreatePollDao {

    @Query("SELECT * FROM DbCreatePoll WHERE $COL_POLL_ATTEMPTED LIKE :isAttempted ORDER BY $COL_CREATED_AT DESC")
    fun getPoll(isAttempted: Int): MutableList<DbCreatePoll>

    @Query("SELECT * FROM DbCreatePoll ORDER BY id DESC LIMIT 1")
    fun getLastPoll(): Int

    @Insert
    fun addPoll(vararg dbCreatePoll: DbCreatePoll)

    @Query("UPDATE DbCreatePoll SET $COL_POLL_ATTEMPTED = :isAttempted WHERE id = :id")
    fun attemptPoll(isAttempted: Int, id: Int)
}