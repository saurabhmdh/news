package com.axion.news.database.table

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "author")
@Parcelize
data class Author (
    @PrimaryKey
    val id: String,
    val name: String?,
    val slug: String,

    @SerializedName("profile_image") val profileImage: String?,
    @SerializedName("cover_image") val coverImage: String?,

    val bio: String?,
    val website: String?,
    val location: String?,
    val facebook: String?,
    val twitter: String?,

    @SerializedName("meta_title") val metaTitle: String?,
    @SerializedName("meta_description") val metaDescription: String?,
    val url: String?
):Parcelable