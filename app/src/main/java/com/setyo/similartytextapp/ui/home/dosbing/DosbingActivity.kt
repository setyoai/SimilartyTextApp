package com.setyo.similartytextapp.ui.home.dosbing

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.similartytextapp.databinding.ActivityDosbingBinding
import com.setyo.similartytextapp.ui.ViewModelFactory
import com.setyo.similartytextapp.ui.main.MainActivity

class DosbingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDosbingBinding
    private lateinit var factory: ViewModelFactory
    private val dosbingViewModel: DosbingViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDosbingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupView()

        dosbingViewModel.getDosen().observe(this) {
            getResult(it.id_dosen)
        }
        binding.dosbingList.adapter = DosbingAdapter(emptyList())
        showRecyclerView()
    }

    private fun setupView(){
        binding.toolbarDosbing.imageViewBack.setOnClickListener {
            val intent = Intent(this@DosbingActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun getResult(id: String) {
        dosbingViewModel.getDataDosenBimbingan(id)
        dosbingViewModel.bimbinganDosenResponse.observe(this) {
            binding.dosbingList.adapter = DosbingAdapter(it.bimbinganDosenData)
        }
    }

    private fun showLoading() {
        dosbingViewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showRecyclerView() {
        binding.dosbingList.apply {
            layoutManager = LinearLayoutManager(this@DosbingActivity)
            setHasFixedSize(true)
        }
    }
}