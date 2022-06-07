package org.firmanmardiyanto.yourmate.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    val key: String? = null,
    val from: String? = null,
    val text: String? = null,
    val type: String = "text",
    val hasRead: Boolean = false,
    val readAt: Long? = null,
    val timestamp: Long? = null,
    val firstMessageInDays: Boolean = false
) : Parcelable {
    fun toMessageWithFirstMessageInDays(firstMessageInDays: Boolean): Message {
        return Message(
            key = key,
            from = from,
            text = text,
            type = type,
            hasRead = hasRead,
            readAt = readAt,
            timestamp = timestamp,
            firstMessageInDays = firstMessageInDays
        )
    }
}

