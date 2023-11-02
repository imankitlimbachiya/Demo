package com.app.qfonapp.util

object Config {

    ///////////////////////////////////////////////////////////////////////////
    // Application Constants
    ///////////////////////////////////////////////////////////////////////////

    const val do_ = true
    const val doNot_ = false

    const val ZERO = 0
    const val ONE = 1

    const val TIMER_ONE = 1000
    const val TIMER_THREE = 3000

    ///////////////////////////////////////////////////////////////////////////
    // Room Constants
    ///////////////////////////////////////////////////////////////////////////

    const val DB_QFONAPP = "DbQfonapp"
    const val DB_VERSION = 4

    const val COL_POLL_ID = "poll_id"
    const val COL_POLL_TITLE = "poll_title"
    const val COL_POLL_OPTION = "poll_option"
    const val COL_POLL_ATTEMPTED = "poll_attempted"
    const val COL_OPTION_ATTEMPTED = "option_attempted"
    const val COL_CREATED_AT = "created_at"
    const val COL_UPDATED_AT = "updated_at"
}