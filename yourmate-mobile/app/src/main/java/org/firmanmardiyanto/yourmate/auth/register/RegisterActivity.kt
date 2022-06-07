package org.firmanmardiyanto.yourmate.auth.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.firmanmardiyanto.yourmate.MainActivity
import org.firmanmardiyanto.yourmate.auth.login.LoginActivity
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivityRegisterBinding
import org.firmanmardiyanto.yourmate.home.HomeActivity
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        addListener()
    }

    private fun addListener() {
        with(binding) {
            tvLogin.setOnClickListener {
                finish()
            }

            btnRegister.setOnClickListener {
                if (etEmail.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty() || etName.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please fill all field",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    authViewModel.register(
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        etName.text.toString()
                    )
                        .observe(this@RegisterActivity) {
                            when (it) {
                                is Resource.Loading -> {
                                    btnRegister.isEnabled = false
                                }
                                is Resource.Success -> {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Register Success",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            this@RegisterActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                                is Resource.Error -> {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        it.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    btnRegister.isEnabled = true
                                }
                            }
                        }
                }
            }
        }
    }
}