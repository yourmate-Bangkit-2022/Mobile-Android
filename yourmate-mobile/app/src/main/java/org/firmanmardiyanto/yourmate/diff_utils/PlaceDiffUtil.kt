package org.firmanmardiyanto.yourmate.diff_utils

import androidx.recyclerview.widget.DiffUtil
import org.firmanmardiyanto.yourmate.domain.model.Place

class PlaceDiffUtil : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem == newItem
    }
}