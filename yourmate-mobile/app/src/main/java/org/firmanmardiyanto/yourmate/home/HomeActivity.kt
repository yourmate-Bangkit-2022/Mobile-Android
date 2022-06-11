package org.firmanmardiyanto.yourmate.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.databinding.ActivityHomeBinding
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        binding.apply {
            bottomNavView.setupWithNavController(navController)
        }
    }

}