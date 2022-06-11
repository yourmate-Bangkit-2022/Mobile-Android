package org.firmanmardiyanto.yourmate.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.databinding.ActivityHomeBinding
import org.firmanmardiyanto.yourmate.home.ui.contacts.ContactsFragment
import org.firmanmardiyanto.yourmate.home.ui.dashboard.ChatFragment
import org.firmanmardiyanto.yourmate.home.ui.home.HomeFragment
import org.firmanmardiyanto.yourmate.home.ui.profile.ProfileFragment

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val homeFragment by lazy { HomeFragment() }
    private val chatFragment by lazy { ChatFragment() }
    private val contactFragment by lazy { ContactsFragment() }
    private val profileFragment by lazy { ProfileFragment() }

    private var currentFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initChildFragment()
    }

    private fun initUI() {
        with(binding) {
            bottomNavView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> {
                        navigateToFragment(homeFragment)
                        true
                    }
                    R.id.navigation_chat -> {
                        navigateToFragment(chatFragment)
                        true
                    }
                    R.id.navigation_contact -> {
                        navigateToFragment(contactFragment)
                        true
                    }
                    R.id.navigation_profile -> {
                        navigateToFragment(profileFragment)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun initChildFragment() {
        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.home_fragment_container, profileFragment)
                .hide(profileFragment)
                .add(R.id.home_fragment_container, contactFragment)
                .hide(contactFragment)
                .add(R.id.home_fragment_container, chatFragment)
                .hide(chatFragment)
                .add(R.id.home_fragment_container, homeFragment)
                .commit()
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(currentFragment).also {
                currentFragment = fragment
            }
            .show(fragment)
            .commit()
    }

}