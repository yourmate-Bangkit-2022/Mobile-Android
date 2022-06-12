package org.firmanmardiyanto.yourmate.home.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.adapters.ContactsAdapter
import org.firmanmardiyanto.yourmate.chat.ChatActivity
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.FragmentChatBinding
import org.firmanmardiyanto.yourmate.item_decoration.SpacingItemDecoration
import org.firmanmardiyanto.yourmate.utils.extensions.showToast
import org.firmanmardiyanto.yourmate.viewmodels.ChatViewModel
import org.firmanmardiyanto.yourmate.viewmodels.ContactsViewModel

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private val contactsViewModel: ContactsViewModel by viewModels()

    private var _binding: FragmentChatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val spacingItemDecoration by lazy { SpacingItemDecoration(32) }

    private val contactsAdapter by lazy {
        ContactsAdapter().setOnClickListener {
            val intent = Intent(requireContext(), ChatActivity::class.java).apply {
                putExtra(ChatActivity.EXTRA_USER, it)
            }
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        loadContacts()
    }

    private fun initUI() {
        with(binding) {
            srlChat.setOnRefreshListener {
                srlChat.isRefreshing = false
                loadContacts()
            }

            with(rvContacts) {
                adapter = contactsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(spacingItemDecoration)
            }
        }
    }

    private fun loadContacts() {
        with(binding) {
            contactsViewModel.getAllContacts().observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Error -> {
                        clEmpty.isVisible = true
                        cpiLoading.isVisible = false
                        rvContacts.isVisible = false
                        showToast(it.message)
                    }
                    is Resource.Loading -> {
                        clEmpty.isVisible = false
                        cpiLoading.isVisible = true
                        rvContacts.isVisible = false
                    }
                    is Resource.Success -> {
                        cpiLoading.isVisible = false
                        if (it.data.isNullOrEmpty()) {
                            clEmpty.isVisible = true
                            rvContacts.isVisible = false
                        } else {
                            clEmpty.isVisible = false
                            rvContacts.isVisible = true
                        }
                        contactsAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}