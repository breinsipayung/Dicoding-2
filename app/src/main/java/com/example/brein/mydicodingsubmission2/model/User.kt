package com.example.brein.mydicodingsubmission2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var username: String,
    var avatar: String,
    var repo: String,
    var following: String,
    var follower: String,
    var location: String,
    var company: String
):Parcelable
