package org.firmanmardiyanto.yourmate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val location: String,
    val desc: String,
    val rating: Double,
    val category: String,
    val createdAt: String?,
) : Parcelable
