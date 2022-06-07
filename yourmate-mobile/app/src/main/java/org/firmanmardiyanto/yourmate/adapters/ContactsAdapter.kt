package org.firmanmardiyanto.yourmate.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.databinding.UserItemBinding
import org.firmanmardiyanto.yourmate.domain.model.User

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    private var contacts: List<User> = ArrayList()
    var onItemClick: ((User, UserItemBinding) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding =
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    fun submitList(newContacts: List<User>) {
        val diffCallback = ContactsDiffCallback(contacts, newContacts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        contacts = newContacts
        diffResult.dispatchUpdatesTo(this)
        Log.d("ContactsAdapter", "submitList: $contacts")
    }


    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    inner class ContactsViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: User) {
            with(binding) {
                tvName.text = contact.name
                tvLastMessage.text = "-"
                Glide.with(itemView.context)
                    .load(contact.profileImage)
                    .error(R.drawable.ic_image)
                    .into(imageView)
            }

            Log.d("ContactsAdapter", "bind: $contact")
            binding.root.setOnClickListener {
                onItemClick?.invoke(contact, binding)
            }
        }

    }

}

class ContactsDiffCallback(private val contacts: List<User>, private val newContacts: List<User>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return contacts.size
    }

    override fun getNewListSize(): Int {
        return newContacts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return contacts[oldItemPosition].uid == newContacts[newItemPosition].uid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return contacts[oldItemPosition] == newContacts[newItemPosition]
    }

}
