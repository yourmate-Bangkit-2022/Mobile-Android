package org.firmanmardiyanto.yourmate.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.firmanmardiyanto.yourmate.MainActivity
import org.firmanmardiyanto.yourmate.auth.register.RegisterActivity
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivityLoginBinding
import org.firmanmardiyanto.yourmate.home.HomeActivity
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        addListener()
    }

    private fun addListener() {
        with(binding) {
            tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
            btnLogin.setOnClickListener {
                if (etEmail.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Email or Password is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    authViewModel.login(etEmail.text.toString(), etPassword.text.toString())
                        .observe(this@LoginActivity) {
                            when (it) {
                                is Resource.Loading -> {
                                    btnLogin.isEnabled = false
                                    btnLogin.text = "Loading..."
                                }
                                is Resource.Success -> {
                                    btnLogin.isEnabled = true
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Login Success",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            this@LoginActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                                is Resource.Error -> {
                                    btnLogin.isEnabled = true
                                    btnLogin.text = "Login"
                                    Toast.makeText(
                                        this@LoginActivity,
                                        it.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                }
            }
        }
    }

}