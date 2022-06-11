package org.firmanmardiyanto.yourmate.chat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.adapters.MessageAdapter
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivityChatBinding
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.viewmodels.ChatViewModel

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var selectedContact: User
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        selectedContact = intent.getParcelableExtra(EXTRA_USER)!!
//
//        messageAdapter = MessageAdapter()
//        binding.rvChats.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//
//        binding.rvChats.adapter = messageAdapter
//
//        getChats()
//
//        addListener()
    }

    private fun getChats() {
//        selectedContact.let { user ->
//            chatViewModel.getMessagesWith(user.uid!!).observe(this) { chatsResponse ->
//                when (chatsResponse) {
//                    is Resource.Loading -> {
//                        binding.progressBar.visibility = android.view.View.VISIBLE
//                    }
//                    is Resource.Success -> {
//                        binding.progressBar.visibility = android.view.View.GONE
//                        chatsResponse.data?.let {
//                            messageAdapter.submitList(it)
//                        }
//                    }
//                    is Resource.Error -> {
//                        binding.progressBar.visibility = android.view.View.GONE
//                        Toast.makeText(this, "Error ${chatsResponse.message}", Toast.LENGTH_SHORT)
//                            .show()
//                        Log.d("ChatActivity", "Error ${chatsResponse.message}")
//                    }
//                }
//            }
//        }
    }

    private fun addListener() {
//        binding.btnSend.setOnClickListener {
//            val message = binding.etMessage.text.toString()
//            if (message.isNotEmpty()) {
//                chatViewModel.sendMessageTo(selectedContact.uid!!, message).observe(this) {
//                    when (it) {
//                        is Resource.Success -> {
//                            binding.etMessage.text.clear()
//                            binding.etMessage.setText("")
//                            binding.btnSend.isEnabled = true
//                            getChats()
//                        }
//                        is Resource.Error -> {
//                            Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
//                            binding.btnSend.isEnabled = true
//
//                        }
//                        is Resource.Loading -> {
//                            binding.btnSend.isEnabled = false
//                        }
//                    }
//                }
//            }
//        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}