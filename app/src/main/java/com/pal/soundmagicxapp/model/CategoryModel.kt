package com.pal.soundmagicxapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val name:String ="",
    val imgUrl:String = "",
    val songs:List<String> = listOf()
) : Parcelable
