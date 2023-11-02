package com.app.qfonapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.qfonapp.util.Config.COL_OPTION_ATTEMPTED
import com.app.qfonapp.util.Config.COL_POLL_ID

@Dao
interface DbPollOptionDao {

    @Query("SELECT * FROM DbPollOption WHERE $COL_POLL_ID LIKE :pollId")
    fun getPollOption(pollId: Int): MutableList<DbPollOption>

    @Insert
    fun addOption(vararg dbPollOption: DbPollOption)

    @Query("UPDATE DbPollOption SET $COL_OPTION_ATTEMPTED = :isAttempted WHERE id = :id")
    fun attemptOption(isAttempted: Boolean, id: Int)
}