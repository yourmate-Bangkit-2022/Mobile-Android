package org.firmanmardiyanto.yourmate.adapters

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.databinding.LayoutItemArticleBinding
import org.firmanmardiyanto.yourmate.diff_utils.ArticleDiffUtil
import org.firmanmardiyanto.yourmate.domain.model.Article
import java.util.*

class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            LayoutItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ArticleViewHolder(private val binding: LayoutItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(article.imageUrl)
                    .error(R.drawable.ic_image)
                    .into(ivThumbnailArticle)
                tvTitleArticle.text = article.title
                tvBodyArticle.text = article.body
                val cal = Calendar.getInstance()
                cal.set(article.publishedAt.substring(0, 4).toInt(),
                    article.publishedAt.substring(5, 7).toInt() - 1,
                    article.publishedAt.substring(8, 10).toInt())
                tvArticleDate.text = DateFormat.format("dd MMMM yyyy", cal)
            }
        }
    }

}