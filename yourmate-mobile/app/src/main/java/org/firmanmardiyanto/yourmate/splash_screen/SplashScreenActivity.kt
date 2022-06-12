package org.firmanmardiyanto.yourmate.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.auth.login.LoginActivity
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivitySplashScreenBinding
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.home.HomeActivity
import org.firmanmardiyanto.yourmate.utils.extensions.showToast
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var binding: ActivitySplashScreenBinding

    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.currentUser.observe(this) {
            when (it) {
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    currentUser = it.data
                    checkToken()
                }
                is Resource.Error -> {
                    navigateToLogin()
                }
            }
        }
    }

    private fun checkToken() {
        authViewModel.currentMessagingToken.observe(this) {
            when (it) {
                is Resource.Error -> {
                    showToast("Gagal mendapatkan token")
                    navigateToHome()
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    if (currentUser?.messagingToken != it.data && it.data != null) {
                        updateMessagingToken(it.data)
                    }
                }
            }
        }
    }

    private fun updateMessagingToken(token: String) {
        authViewModel.updateMessagingToken(token).observe(this) {
            when (it) {
                is Resource.Error -> {
                    updateMessagingToken(token)
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    navigateToHome()
                }
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}