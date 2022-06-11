package org.firmanmardiyanto.yourmate.auth.register

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
import org.firmanmardiyanto.yourmate.base.LoadingDialog
import org.firmanmardiyanto.yourmate.data.Resource
import org.firmanmardiyanto.yourmate.databinding.ActivityRegisterBinding
import org.firmanmardiyanto.yourmate.home.HomeActivity
import org.firmanmardiyanto.yourmate.utils.extensions.getColorFromAttr
import org.firmanmardiyanto.yourmate.utils.extensions.showToast
import org.firmanmardiyanto.yourmate.viewmodels.AuthViewModel

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val authViewModel: AuthViewModel by viewModels()

    private val loadingDialog by lazy { LoadingDialog.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        setWhiteStatusBar()
        setUpLoginNowSpanText()

        with(binding) {
            btnRegister.setOnClickListener {
                if (etEmail.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty() || etName.text.isNullOrEmpty()) {
                    showToast("Mohon untuk isi semua kolom")
                } else if (etPassword.text.toString() != etRepeatPassword.text.toString()) {
                    showToast("Password tidak sesuai")
                } else {
                    authViewModel.register(
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        etName.text.toString()
                    )
                        .observe(this@RegisterActivity) {
                            when (it) {
                                is Resource.Loading -> {
                                    loadingDialog.show(supportFragmentManager)
                                    btnRegister.isEnabled = false
                                }
                                is Resource.Success -> {
                                    loadingDialog.dismiss()
                                    startActivity(
                                        Intent(
                                            this@RegisterActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                                is Resource.Error -> {
                                    loadingDialog.dismiss()
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

    private fun setWhiteStatusBar() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.let {
            it.isAppearanceLightStatusBars = true
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }

    private fun setUpLoginNowSpanText() {
        val loginNowText = getString(R.string.login_now)

        val startIndex = loginNowText.indexOf("Login Sekarang")
        val endIndex = startIndex + "Login Sekarang".length

        val loginNowSpanText = object : ClickableSpan() {
            override fun onClick(p0: View) {
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color =
                    getColorFromAttr(com.google.android.material.R.attr.colorPrimary)
            }
        }

        val spannableString = SpannableString(loginNowText).apply {
            setSpan(
                loginNowSpanText,
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.tvLoginNow.apply {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
        }
    }
}