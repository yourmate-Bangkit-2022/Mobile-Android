package org.firmanmardiyanto.yourmate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.firmanmardiyanto.yourmate.databinding.LayoutItemReceivedChatBinding
import org.firmanmardiyanto.yourmate.databinding.LayoutItemSentChatBinding
import org.firmanmardiyanto.yourmate.diff_utils.MessageDiffUtil
import org.firmanmardiyanto.yourmate.domain.model.Message
import org.firmanmardiyanto.yourmate.utils.CalendarUtils

class MessageAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).from == "me") {
            VIEW_TYPE_MESSAGE_SENT
        } else {
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            val binding = LayoutItemReceivedChatBinding.inflate(inflater, parent, false)
            ReceivedMessageViewHolder(binding)
        } else {
            val binding = LayoutItemSentChatBinding.inflate(inflater, parent, false)
            SentMessageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (holder) {
                is ReceivedMessageViewHolder -> holder.bind(it)
                is SentMessageViewHolder -> holder.bind(it)
            }
        }
    }

    fun addItem(message: Message) {
        val currentListMessage = currentList.toMutableList()
        val updatedList = currentListMessage.also { it.add(message) }
        super.submitList(updatedList)
    }

    class SentMessageViewHolder(private val binding: LayoutItemSentChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            with(binding) {
                tvMessage.text = message.text
                val calendar = CalendarUtils.formatTimeInMilliesToCalendar(message.timestamp!!)
                tvTime.text =
                    CalendarUtils.formatCalendarToString(calendar, CalendarUtils.TIME_READABLE)

                if (message.firstMessageInDays) {
                    tvDate.isVisible = true
                    tvDate.text = CalendarUtils.formatCalendarToString(
                        calendar,
                        CalendarUtils.DATE_MONTH_YEAR_READABLE
                    )
                }
            }
        }
    }

    class ReceivedMessageViewHolder(private val binding: LayoutItemReceivedChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            with(binding) {
                tvMessage.text = message.text
                val calendar = CalendarUtils.formatTimeInMilliesToCalendar(message.timestamp!!)
                tvTime.text =
                    CalendarUtils.formatCalendarToString(calendar, CalendarUtils.TIME_READABLE)

                if (message.firstMessageInDays) {
                    tvDate.isVisible = true
                    tvDate.text = CalendarUtils.formatCalendarToString(
                        calendar,
                        CalendarUtils.DATE_MONTH_YEAR_READABLE
                    )
                }
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }

}
