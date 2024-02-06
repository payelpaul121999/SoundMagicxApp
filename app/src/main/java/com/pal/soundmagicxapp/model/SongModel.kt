package com.pal.soundmagicxapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SongModel(
    val id: String ? = "",
    val title: String? = "",
    val image: String ?= "",
    val url: String? = ""
):Parcelable
