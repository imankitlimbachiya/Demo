package com.app.qfonapp.model

import com.google.gson.annotations.SerializedName

data class Poll(
    @SerializedName("poll_id") var pollId: Int? = null,
    @SerializedName("poll_title") var pollTitle: String? = null,
    @SerializedName("Option") var option: MutableList<Option> = arrayListOf()
)

data class Option(
    @SerializedName("poll_id") var pollId: Int? = null,
    @SerializedName("poll_option_id") var pollOptionId: Int? = null,
    @SerializedName("poll_option") var pollOption: String? = null,
    @SerializedName("option_attempted") var pollAttempted: Boolean? = null
)
