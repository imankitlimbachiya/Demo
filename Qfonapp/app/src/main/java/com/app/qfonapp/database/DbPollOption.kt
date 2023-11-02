package com.app.qfonapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.qfonapp.util.Config.COL_CREATED_AT
import com.app.qfonapp.util.Config.COL_OPTION_ATTEMPTED
import com.app.qfonapp.util.Config.COL_POLL_ID
import com.app.qfonapp.util.Config.COL_POLL_OPTION
import com.app.qfonapp.util.Config.COL_UPDATED_AT

@Entity
data class DbPollOption(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = COL_POLL_ID) val pollId: Int?,
    @ColumnInfo(name = COL_POLL_OPTION) val pollOption: String?,
    @ColumnInfo(name = COL_OPTION_ATTEMPTED) val optionAttempted: Boolean = false,
    @ColumnInfo(name = COL_CREATED_AT) val createdAt: String?,
    @ColumnInfo(name = COL_UPDATED_AT) val updatedAt: String?
)