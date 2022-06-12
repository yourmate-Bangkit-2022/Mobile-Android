package org.firmanmardiyanto.yourmate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val body: String?,
    val publishedAt: String,
) : Parcelable
