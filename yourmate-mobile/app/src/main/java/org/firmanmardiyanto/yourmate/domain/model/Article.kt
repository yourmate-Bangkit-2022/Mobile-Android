package org.firmanmardiyanto.yourmate.domain.model

data class Article(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val body: String
)
