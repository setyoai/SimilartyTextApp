package com.setyo.similartytextapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.ActivityMainBinding
import com.setyo.similartytextapp.ui.ViewModelFactory
import com.setyo.similartytextapp.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var factory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupUser()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_pendaftaran,
                R.id.navigation_similarity,
                R.id.navigation_penilaian,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val menu = navView.menu

        var userRole: String? = null // Initialize with a default value or null depending on your use case

        mainViewModel.getDosen().observe(this@MainActivity) { userData ->
            userData?.let {
                userRole = it.role
                when (userRole) {
                    "mahasiswa" -> {
                        menu.findItem(R.id.navigation_penilaian).isVisible = false
                    }
                    // Handle other roles as needed
                    else -> {
                        menu.findItem(R.id.navigation_pendaftaran).isVisible = false
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupUser() {
        mainViewModel.getUser().observe(this@MainActivity) {
            if (!it.isLogin) {
                moveActivity()
            }
        }
        showToast()
    }

    private fun moveActivity() {
        startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
        finish()
    }

    private fun showToast() {
        mainViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        var mToken = "token"
    }
}