package com.setyo.similartytextapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.ActivityLoginUserBinding
import com.setyo.similartytextapp.model.DosenModel
import com.setyo.similartytextapp.ui.ViewModelFactory
import com.setyo.similartytextapp.ui.main.MainActivity

class LoginUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginUserBinding
    private lateinit var factory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels{factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)
        binding = ActivityLoginUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()
    }


    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupAction() {
        binding.apply {
            buttonLogin.setOnClickListener {
                val email = inputTextUsername.text.toString()
                val password = inputTextPassword.text.toString()

                if (email.isEmpty() && password.isEmpty()) {
                    inputTextUsername.error = getString(R.string.error_textField)
                    inputTextPassword.setError(getString(R.string.error_textField), null)
                } else if (email.isNotEmpty() && password.isNotEmpty()) {
                    showLoading()
                    postText()
                    showToast()
                    loginViewModel.getToken()
                    moveActivity()
                }
            }
        }
    }

    private fun postText() {
        binding.apply {
            loginViewModel.loginUserDosen(
                username = inputTextUsername.text.toString(),
                password = inputTextPassword.text.toString()
            )
        }

        loginViewModel.loginUserResponse.observe(this@LoginUserActivity) {
            getLoginDosen(
                DosenModel(
                    it.loginResult.idUser.toString(),
                    it.loginResult.usernameUser.toString(),
                    it.loginResult.idDosen.toString(),
                    it.loginResult.role.toString(),
                    it.loginResult.token.toString(),
                    true
                )
            )
        }
    }

    private fun getLoginDosen(dosen: DosenModel) {
        loginViewModel.getLoginDosen(dosen)
    }

    private fun moveActivity() {
        loginViewModel.loginUserResponse.observe(this@LoginUserActivity) {
            if (!it.error) {
                startActivity(Intent(this@LoginUserActivity, MainActivity::class.java))
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                finish()
            }
        }
    }

    private fun showToast() {
        loginViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading() {
        loginViewModel.isLoading.observe(this@LoginUserActivity) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}