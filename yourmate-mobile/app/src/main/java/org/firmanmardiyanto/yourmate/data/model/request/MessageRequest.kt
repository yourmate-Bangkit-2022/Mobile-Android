package org.firmanmardiyanto.yourmate.data.model.request

data class MessageRequest(
    val notification: NotificationMessageRequest,
    val data: DataMessageRequest,
    val to: String
)

data class NotificationMessageRequest(
    val title: String,
    val body: String
)

data class DataMessageRequest(
    val message: String
)