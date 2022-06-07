package org.firmanmardiyanto.yourmate

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import org.firmanmardiyanto.yourmate.auth.login.LoginActivity
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivityMainBinding
import org.firmanmardiyanto.yourmate.home.HomeActivity
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModel()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.currentUser.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.tvInfo.text = "Loading..."
                }
                is Resource.Success -> {
//                    Toast.makeText(this, "Welcome ${it.data!!.uid}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()
                }
                is Resource.Error -> {
                    binding.tvInfo.text = it.message
                    navigateToLogin()
                }
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}