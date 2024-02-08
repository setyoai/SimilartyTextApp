package com.setyo.similartytextapp.ui.home.bimbingan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.setyo.similartytextapp.data.remote.response.DafskripsiData
import com.setyo.similartytextapp.data.remote.response.DosbingmhsData
import com.setyo.similartytextapp.databinding.ActivityBimbinganBinding
import com.setyo.similartytextapp.ui.ViewModelFactory
import com.setyo.similartytextapp.ui.main.MainActivity

class BimbinganActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBimbinganBinding
    private lateinit var factory: ViewModelFactory
    private val bimbinganModel: BimbinganViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBimbinganBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setDafSkripsi()
        setupView()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding.toolbarBimbingan.imageViewBack.setOnClickListener {
            val intent = Intent(this@BimbinganActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setDafSkripsi(){
        bimbinganModel.getUser().observe(this) {
            setDafSkripsi(it.nim_mhs)
        }
        bimbinganModel.resultDafSkripsiResponse.observe(this) {
            val dafskripsiData = it.dafskripsiData
            getResult(dafskripsiData)
        }
    }

    private fun getResult(resultData: DafskripsiData) {
        setMhsDosbing(resultData.idDafskripsi)
        bimbinganModel.dosbingMhsResponse.observe(this) {
            val dafskripsiData = it.dosbingmhsData
            getMhsDosbingData(dafskripsiData)
        }
    }

    private fun getMhsDosbingData(dosbingMhsData: DosbingmhsData) {
        binding.apply {
            textViewDosen1.text = dosbingMhsData.nama1Dosbing
            textViewDosen2.text = dosbingMhsData.nama2Dosbing
        }
        binding.cardViewPembimbingUtama.setOnClickListener {
            val intent = Intent(this@BimbinganActivity, BimbinganDosen1Activity::class.java)
            intent.putExtra(BimbinganDosen1Activity.EXTRA_NAME_DOSEN1, dosbingMhsData.nama1Dosbing)
            intent.putExtra(BimbinganDosen1Activity.EXTRA_DOSBING_ID, dosbingMhsData.dosbingId)
            intent.putExtra(BimbinganDosen1Activity.EXTRA_DOSEN1_ID, dosbingMhsData.dosen1Dosbing)
            startActivity(intent)
        }
        binding.cardViewPendamping.setOnClickListener {
            val intent = Intent(this@BimbinganActivity, BimbinganDosen2Activity::class.java)
            intent.putExtra(BimbinganDosen2Activity.EXTRA_NAME_DOSEN2, dosbingMhsData.nama2Dosbing)
            intent.putExtra(BimbinganDosen2Activity.EXTRA_DOSBING_ID, dosbingMhsData.dosbingId)
            intent.putExtra(BimbinganDosen2Activity.EXTRA_DOSEN2_ID, dosbingMhsData.dosen2Dosbing)
            startActivity(intent)
        }
    }

    private fun setDafSkripsi(id: String) {
        bimbinganModel.getDafSkripsi(id)
    }

    private fun setMhsDosbing(id: String) {
        bimbinganModel.getMhsDosbing(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }
}