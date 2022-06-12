package org.firmanmardiyanto.yourmate.services

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.firmanmardiyanto.yourmate.domain.model.Message

private const val TAG = "YourMateFirebaseMessagi"

class YourMateFirebaseMessagingService : FirebaseMessagingService() {

    private val broadcastManager by lazy { LocalBroadcastManager.getInstance(this) }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val msg = Message(
            text = message.data["message"],
            timestamp = System.currentTimeMillis(),
            from = "you"
        )
        broadcastMessage(msg)
    }

    private fun broadcastMessage(message: Message) {
        val intent = Intent(RECEIVED_MESSAGE_ACTION).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        broadcastManager.sendBroadcast(intent)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
    }

    companion object {
        const val RECEIVED_MESSAGE_ACTION = "RECEIVED_MESSAGE_ACTION"
        const val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    }

}