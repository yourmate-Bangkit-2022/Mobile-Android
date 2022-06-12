package org.firmanmardiyanto.yourmate.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.adapters.ArticleAdapter
import org.firmanmardiyanto.yourmate.adapters.FriendRecommendationAdapter
import org.firmanmardiyanto.yourmate.adapters.PlaceAdapter
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.FragmentHomeBinding
import org.firmanmardiyanto.yourmate.item_decoration.SpacingItemDecoration
import org.firmanmardiyanto.yourmate.utils.CalendarUtils
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()

    private val homeViewModel: HomeViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null

    private val friendRecommendationAdapter by lazy { FriendRecommendationAdapter() }
    private val articleAdapter by lazy { ArticleAdapter() }
    private val placeAdapter by lazy { PlaceAdapter() }

    private val spacingItemDecoration by lazy { SpacingItemDecoration(orientation = SpacingItemDecoration.ORIENTATION.HORIZONTAL) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initArticle()
        initPlace()
//        FirebaseMessaging.getInstance().send(RemoteMessage())
    }

    private fun initUI() {
        with(binding) {
            tvDate.text = CalendarUtils.formatCalendarToString(
                Calendar.getInstance(),
                CalendarUtils.DATE_MONTH_YEAR_READABLE
            )

            authViewModel.currentUser.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        Glide.with(requireContext())
                            .load(it.data?.profileImage)
                            .error(R.drawable.ic_image)
                            .into(ivProfilePicture)

                        tvName.text = "Hi, ${it.data?.name}!"
                        //  TODO : get user's friend recommendation
                        initFriend(1)
                    }
                }
            }

            with(rvPeople) {
                adapter = friendRecommendationAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(spacingItemDecoration)
            }

            with(rvArticle) {
                adapter = articleAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(spacingItemDecoration)
            }

            with(rvPlace) {
                adapter = placeAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(spacingItemDecoration)
            }
        }
    }

    private fun initFriend(userId: Int) {
        homeViewModel.getUserById(userId).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    val user = result.data
                    friendRecommendationAdapter.submitList(user?.recommendations)
                }
            }
        }
    }

    private fun initArticle() {
        homeViewModel.articles.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    articleAdapter.submitList(it.data)
                }
            }
        }
    }

    private fun initPlace() {
        homeViewModel.places.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    placeAdapter.submitList(it.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}