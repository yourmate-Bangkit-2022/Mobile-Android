package org.firmanmardiyanto.yourmate.home.ui.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.adapters.ContactsAdapter
import org.firmanmardiyanto.yourmate.chat.ChatActivity
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.FragmentContactsBinding
import org.firmanmardiyanto.yourmate.viewmodels.ContactsViewModel

@AndroidEntryPoint
class ContactsFragment : Fragment() {
    private val contactsViewModel: ContactsViewModel by viewModels()
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val adapter = ContactsAdapter()
        adapter.onItemClick = { selectedContact, _ ->
            val intent = Intent(activity, ChatActivity::class.java)
            intent.putExtra(ChatActivity.EXTRA_USER, selectedContact)
            startActivity(intent)
        }
        binding.rvUsers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        contactsViewModel.contacts.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbContacts.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbContacts.visibility = View.GONE
                    Log.d("ContactsFragment", "Success" + it.data?.size)
                    it.data?.let { contacts -> adapter.submitList(contacts) }
                    binding.rvUsers.adapter = adapter
                }
                is Resource.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    binding.pbContacts.visibility = View.GONE
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}