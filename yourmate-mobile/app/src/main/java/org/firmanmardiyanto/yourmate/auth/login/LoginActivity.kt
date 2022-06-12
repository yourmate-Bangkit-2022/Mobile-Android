package org.firmanmardiyanto.yourmate.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import dagger.hilt.android.AndroidEntryPoint
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.auth.register.RegisterActivity
import org.firmanmardiyanto.yourmate.base.LoadingDialog
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivityLoginBinding
import org.firmanmardiyanto.yourmate.domain.model.User
import org.firmanmardiyanto.yourmate.home.HomeActivity
import org.firmanmardiyanto.yourmate.utils.extensions.getColorFromAttr
import org.firmanmardiyanto.yourmate.utils.extensions.showToast
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    private val loadingDialog by lazy { LoadingDialog.create() }

    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        setWhiteStatusBar()
        setUpRegisterSpanText()

        with(binding) {
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
                                    loadingDialog.show(supportFragmentManager)
                                    btnLogin.isEnabled = false
                                    btnLogin.text = "Loading..."
                                }
                                is Resource.Success -> {
                                    btnLogin.isEnabled = true
                                    currentUser = it.data
                                    checkToken()
                                }
                                is Resource.Error -> {
                                    loadingDialog.dismiss()
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

    private fun checkToken() {
        authViewModel.currentMessagingToken.observe(this) {
            when (it) {
                is Resource.Error -> {
                    loadingDialog.dismiss()
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
                    loadingDialog.dismiss()
                    navigateToHome()
                }
            }
        }
    }

    private fun navigateToHome() {
        startActivity(
            Intent(
                this@LoginActivity,
                HomeActivity::class.java
            )
        )
        finish()
    }

    private fun setWhiteStatusBar() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.let {
            it.isAppearanceLightStatusBars = true
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }

    private fun setUpRegisterSpanText() {
        val registerAccountText = getString(R.string.register_account)

        val startIndex = registerAccountText.indexOf("Daftar Sekarang")
        val endIndex = startIndex + "Daftar Sekarang".length

        val registerAccountSpanText = object : ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color =
                    getColorFromAttr(com.google.android.material.R.attr.colorPrimary)
            }
        }

        val spannableString = SpannableString(registerAccountText).apply {
            setSpan(
                registerAccountSpanText,
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.tvRegister.apply {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

}