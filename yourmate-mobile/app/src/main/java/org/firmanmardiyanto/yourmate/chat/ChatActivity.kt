package org.firmanmardiyanto.yourmate.chat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.postDelayed
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.adapters.MessageAdapter
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivityChatBinding
import org.firmanmardiyanto.yourmate.domain.model.Message
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.item_decoration.SpacingItemDecoration
import org.firmanmardiyanto.yourmate.services.YourMateFirebaseMessagingService
import org.firmanmardiyanto.yourmate.utils.extensions.showToast
import org.firmanmardiyanto.yourmate.viewmodels.ChatViewModel

private const val TAG = "ChatActivity"

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    private val chatViewModel: ChatViewModel by viewModels()

    private lateinit var binding: ActivityChatBinding

    private val spacingItemDecoration by lazy { SpacingItemDecoration(32) }

    private val messageAdapter: MessageAdapter by lazy { MessageAdapter() }

    private val broadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(content: Context?, intent: Intent?) {
                try {
                    val message =
                        intent?.getParcelableExtra<Message>(YourMateFirebaseMessagingService.EXTRA_MESSAGE)
                    messageAdapter.addItem(message!!)
                    binding.rvChat.smoothScrollToPosition(messageAdapter.itemCount)
                } catch (e: Exception) {
                    Log.e(TAG, "onReceive: error received message", e)
                }
            }
        }
    }

    private lateinit var selectedContact: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedContact = intent.getParcelableExtra(EXTRA_USER)!!

        initUI()
        loadMessages()

        LocalBroadcastManager.getInstance(this).registerReceiver(
            broadcastReceiver,
            IntentFilter(YourMateFirebaseMessagingService.RECEIVED_MESSAGE_ACTION)
        )
    }

    private fun initUI() {
        with(binding) {
            flBackButton.setOnClickListener {
                finish()
            }

            Glide.with(this@ChatActivity)
                .load(selectedContact.profileImage)
                .error(R.drawable.ic_image)
                .into(ivProfilePicture)

            tvName.text = selectedContact.name

            with(rvChat) {
                adapter = messageAdapter
                layoutManager = LinearLayoutManager(this@ChatActivity)
                addItemDecoration(spacingItemDecoration)
                addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
                    if (bottom < oldBottom) {
                        binding.rvChat.smoothScrollToPosition(messageAdapter.itemCount)
                    }
                }
            }

            btnSend.setOnClickListener {
                sendMessage()
            }
        }
    }

    private fun sendMessage() {
        with(binding) {
            if (!etChat.text.isNullOrBlank()) {
                chatViewModel.sendMessageTo(
                    selectedContact.uid!!,
                    etChat.text.toString(),
                    selectedContact.messagingToken!!
                )
                    .observe(this@ChatActivity) {
                        when (it) {
                            is Resource.Error -> {
                                btnSend.isEnabled = true
                                showToast(it.message)
                            }
                            is Resource.Loading -> {
                                btnSend.isEnabled = false
                            }
                            is Resource.Success -> {
                                btnSend.isEnabled = true
                                etChat.text = null
                                messageAdapter.addItem(it.data!!)
                                binding.rvChat.smoothScrollToPosition(messageAdapter.itemCount)
                            }
                        }
                    }
            }
        }
    }

    private fun loadMessages() {
        selectedContact.let { user ->
            chatViewModel.getMessagesWith(user.uid!!).observe(this) { chatsResponse ->
                when (chatsResponse) {
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        chatsResponse.data?.let {
                            messageAdapter.submitList(it)
                            binding.rvChat.smoothScrollToPosition(messageAdapter.itemCount)
                        }
                    }
                    is Resource.Error -> {
                        showToast(chatsResponse.message)
                        Log.e("ChatActivity", "Error ${chatsResponse.message}")
                    }
                }
            }
        }
    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
        super.onStop()
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}