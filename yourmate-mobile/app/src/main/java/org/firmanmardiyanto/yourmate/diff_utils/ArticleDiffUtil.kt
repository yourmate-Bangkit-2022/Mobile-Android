package org.firmanmardiyanto.yourmate.diff_utils

import androidx.recyclerview.widget.DiffUtil
import org.firmanmardiyanto.yourmate.domain.model.Article

class ArticleDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}