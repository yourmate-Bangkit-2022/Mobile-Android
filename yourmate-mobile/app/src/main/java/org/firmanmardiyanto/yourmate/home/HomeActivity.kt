package org.firmanmardiyanto.yourmate.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.auth.login.LoginActivity
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivityHomeBinding
import org.firmanmardiyanto.yourmate.settings.SettingsActivity
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_chat, R.id.navigation_contact
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.action_logout -> {
                authViewModel.logout().observe(this) {
                    when (it) {
                        is Resource.Loading -> {
                            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Success -> {
                            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        is Resource.Error -> {
                            Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}