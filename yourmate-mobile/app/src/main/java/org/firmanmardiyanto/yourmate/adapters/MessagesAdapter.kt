package org.firmanmardiyanto.yourmate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.firmanmardiyanto.yourmate.databinding.ReceivedChatItemBinding
import org.firmanmardiyanto.yourmate.databinding.SentChatItemBinding
import org.firmanmardiyanto.yourmate.domain.model.Message
import java.sql.Date
import java.text.SimpleDateFormat

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    private var messages: List<Message> = ArrayList()
    var onItemClick: ((Message) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            val binding =
                ReceivedChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageViewHolder(receivedChatItemBinding = binding, viewType = viewType)
        } else {
            val binding =
                SentChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageViewHolder(sentBinding = binding, viewType = viewType)
        }
    }

    fun submitList(newMessages: List<Message>) {
        val diffCallback = MessagesDiffCallback(messages, newMessages)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        messages = newMessages
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    inner class MessageViewHolder(
        private val sentBinding: SentChatItemBinding? = null,
        private val receivedChatItemBinding: ReceivedChatItemBinding? = null,
        private val viewType: Int = VIEW_TYPE_MESSAGE_RECEIVED
    ) :
        RecyclerView.ViewHolder(if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) receivedChatItemBinding!!.root else sentBinding!!.root) {
        fun bind(message: Message) {
            if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
                val binding = receivedChatItemBinding!!
                binding.tvMessageMe.text = message.text
                val date = Date(message.timestamp!!)
                binding.tvTimeMe.text = SimpleDateFormat("HH:mm").format(date)
                binding.tvDateMe.text = SimpleDateFormat("dd MMM yyyy").format(date)
                binding.tvDateMe.visibility = if(message.firstMessageInDays) View.VISIBLE else View.GONE
                binding.root.setOnClickListener {
                    onItemClick?.invoke(message)
                }
            } else {
                val binding = sentBinding!!
                binding.tvMessageOther.text = message.text
                val date = Date(message.timestamp!!)
                binding.tvTimeOther.text = SimpleDateFormat("HH:mm").format(date)
                binding.tvDateOther.text = SimpleDateFormat("dd MMM yyyy").format(date)
                binding.tvDateOther.visibility = if(message.firstMessageInDays) View.VISIBLE else View.GONE
                binding.root.setOnClickListener {
                    onItemClick?.invoke(message)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].from == "me") {
            VIEW_TYPE_MESSAGE_RECEIVED
        } else {
            VIEW_TYPE_MESSAGE_SENT
        }
    }

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }

}

class MessagesDiffCallback(
    private val contacts: List<Message>,
    private val newContacts: List<Message>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return contacts.size
    }

    override fun getNewListSize(): Int {
        return newContacts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return contacts[oldItemPosition].key == newContacts[newItemPosition].key
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return contacts[oldItemPosition] == newContacts[newItemPosition]
    }

}
