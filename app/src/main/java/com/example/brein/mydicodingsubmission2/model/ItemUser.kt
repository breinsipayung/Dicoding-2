package com.example.brein.mydicodingsubmission2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ItemUser (
    val username: String,
    val avatar_url: String
    ): Parcelable
