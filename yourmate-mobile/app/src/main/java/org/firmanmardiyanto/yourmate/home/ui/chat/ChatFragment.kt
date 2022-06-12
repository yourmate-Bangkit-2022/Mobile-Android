package org.firmanmardiyanto.yourmate.home.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.FragmentChatBinding
import org.firmanmardiyanto.yourmate.viewmodels.ChatViewModel

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private val chatViewModel: ChatViewModel by viewModels()

    private var _binding: FragmentChatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        initChats()
    }

    private fun initUI() {
        with(binding) {
            srlChat.isEnabled = false
        }
    }

    private fun initChats() {
        chatViewModel.getAllChats().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
                is Resource.Success -> Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}