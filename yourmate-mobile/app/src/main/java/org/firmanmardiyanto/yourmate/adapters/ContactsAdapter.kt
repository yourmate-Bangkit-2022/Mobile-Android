package org.firmanmardiyanto.yourmate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.databinding.LayoutItemContactBinding
import org.firmanmardiyanto.yourmate.diff_utils.UserDiffUtil
import org.firmanmardiyanto.yourmate.domain.model.User

class ContactsAdapter : ListAdapter<User, ContactsAdapter.ContactViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding =
            LayoutItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ContactViewHolder(private val binding: LayoutItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(user.profileImage)
                    .error(R.drawable.ic_image)
                    .into(ivProfilePicture)

                tvName.text = user.name
                tvEmail.text = user.email
            }
        }
    }

}
