package com.setyo.similartytextapp.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.ui.login.LoginActivity
import com.setyo.similartytextapp.ui.welcome.WelcomeActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            delay(VALUE_SPLASH)
            WelcomeActivity.start(this@SplashActivity)
            finish()
        }
    }

    companion object {
        const val VALUE_SPLASH = 300L
    }
}