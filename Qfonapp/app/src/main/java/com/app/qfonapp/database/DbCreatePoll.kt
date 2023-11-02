package com.app.qfonapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.qfonapp.util.Config.COL_CREATED_AT
import com.app.qfonapp.util.Config.COL_POLL_ATTEMPTED
import com.app.qfonapp.util.Config.COL_POLL_TITLE
import com.app.qfonapp.util.Config.COL_UPDATED_AT

@Entity
data class DbCreatePoll(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = COL_POLL_TITLE) val title: String?,
    @ColumnInfo(name = COL_POLL_ATTEMPTED) val attempted: Int?,
    @ColumnInfo(name = COL_CREATED_AT) val createdAt: String?,
    @ColumnInfo(name = COL_UPDATED_AT) val updatedAt: String?
)