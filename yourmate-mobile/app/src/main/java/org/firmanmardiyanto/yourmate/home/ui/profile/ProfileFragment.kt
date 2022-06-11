package org.firmanmardiyanto.yourmate.home.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.auth.login.LoginActivity
import org.firmanmardiyanto.yourmate.base.LoadingDialog
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.FragmentProfileBinding
import org.firmanmardiyanto.yourmate.utils.extensions.showToast
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private val loadingDialog by lazy { LoadingDialog.create() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        with(binding) {
            authViewModel.currentUser.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        Glide.with(binding.root)
                            .load(it.data?.profileImage)
                            .error(R.drawable.ic_image)
                            .into(ivProfilePicture)
                        tvName.text = it.data?.name ?: "-"
                        tvEmail.text = it.data?.email ?: "-"
                    }
                }
            }

            btnLogout.setOnClickListener {
                authViewModel.logout().observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Error -> {
                            loadingDialog.dismiss()
                            showToast(it.message)
                        }
                        is Resource.Loading -> {
                            loadingDialog.show(childFragmentManager)
                        }
                        is Resource.Success -> {
                            loadingDialog.dismiss()
                            val intent =
                                Intent(requireContext(), LoginActivity::class.java).also { intent ->
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}