package com.app.task.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.task.utils.Configs

@Entity
data class DbOrder(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = Configs.COL_ORDER_ID) val title: String?,
)