package com.axion.news.database.table

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "magazines")
@Parcelize
data class Magazines (
    @PrimaryKey
    val id: String,

    val uuid: String,

    val title: String,

    val slug: String,

    val html: String,

    @SerializedName("comment_id") val CommentID: String,

    @SerializedName("feature_image") val featureImage: String,

    val featured: Boolean,

    val page: Boolean,

    @SerializedName("meta_title") val metaTitle: String?,

    @SerializedName("meta_description") val metaDescription: String?,

    @SerializedName("created_at") val createdAt: Date?,

    @SerializedName("updated_at") val updatedAt: Date?,

    @SerializedName("published_at") val publishAt: Date?,

    @SerializedName("custom_excerpt") val customExcerpt: String?,

    @SerializedName("codeinjection_head") val codeInjectionHead: String?,

    @SerializedName("codeinjection_foot") val codeInjectionFoot: String?,

    @SerializedName("og_image") val ogImage: String?,

    @SerializedName("og_title") val ogTitle: String?,

    @SerializedName("og_description") val ogDescription: String?,

    @SerializedName("twitter_image") val twitterImage: String?,

    @SerializedName("twitter_title") val twitterTitle: String?,

    @SerializedName("twitter_description") val twitterDescription: String?,

    @SerializedName("custom_template") val template: String?,

    @SerializedName("canonical_url") val canonicalUrl: String?,

    @SerializedName("primary_author") val primaryAuthor: String?,

    @SerializedName("primary_tag") val primaryTAG: String?,

    val url: String,

    val excerpt: String
): Parcelable