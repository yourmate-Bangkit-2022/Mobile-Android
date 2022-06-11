package org.firmanmardiyanto.yourmate.diff_utils

import androidx.recyclerview.widget.DiffUtil
import org.firmanmardiyanto.yourmate.domain.model.User

class UserDiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}