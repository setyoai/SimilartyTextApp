package com.setyo.similartytextapp.ui.home.dosbing

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.similartytextapp.data.remote.response.BimbinganResult
import com.setyo.similartytextapp.data.remote.response.UpdateBimbingan
import com.setyo.similartytextapp.databinding.ActivityBimbinganDosbingBinding
import com.setyo.similartytextapp.ui.ViewModelFactory
import com.setyo.similartytextapp.ui.similarity.SimilarityAdapter
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class BimbinganDosenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBimbinganDosbingBinding
    private var getFile: File? = null
    private lateinit var factory: ViewModelFactory
    private val dosbingViewModel: DosbingViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBimbinganDosbingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupView()
        binding.bimbinganViewList.adapter = BimbinganDosbingListAdapter(emptyList())
        showRecyclerView()

    }

        private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding.apply {
            val name = intent.getStringExtra(EXTRA_NAME_MHS)
            val bimbinganId: String = intent.getStringExtra(EXTRA_BIMBINGAN_ID) ?: ""
            getResultBimbingan(bimbinganId)
            toolbarBalasanBimbingan.imageViewBack.setOnClickListener {
                val intent = Intent(this@BimbinganDosenActivity, DosbingActivity::class.java)
                startActivity(intent)
            }
            toolbarBalasanBimbingan.textViewTitleBar.text = name
            toolbarBalasanBimbinganKet.buttonSend.setOnClickListener { sendBimbingan() }
        }
    }

    private fun sendBimbingan() {
        val id: String = intent.getStringExtra(EXTRA_BIMBINGAN_ID) ?: ""
        val balasanketRequesBody = binding.toolbarBalasanBimbinganKet.inputTextSendBimbingan.text.toString()
        val balasanket = balasanketRequesBody
        uploadResponse(id, balasanket)
    }

    private fun uploadResponse( id: String, balasanket: String) {
        dosbingViewModel.updateDataBimbingan(id, balasanket)
        dosbingViewModel.updatebimbinganResponse.observe(this) {
            val updateBimbingan = it.updateBimbingan
            getBimbinganResult(updateBimbingan)
        }
        showToast()
    }

    private fun getBimbinganResult(updateBimbigan: UpdateBimbingan) {

    }


    private fun getResultBimbingan(id: String) {
        dosbingViewModel.getDataUpdateDosbing(id)
        dosbingViewModel.getUpdatebimbinganResponse.observe(this) {
            binding.bimbinganViewList.adapter = BimbinganDosbingListAdapter(it.updateBimbingandosenData)
        }
    }

    private fun showRecyclerView() {
        binding.bimbinganViewList.apply {
            layoutManager = LinearLayoutManager(this@BimbinganDosenActivity)
            setHasFixedSize(true)
        }
    }

    private fun showToast() {
        dosbingViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun showLoading() {
//        dosbingViewModel.isLoading.observe(this@BimbinganMhsActivity) { isLoading ->
//            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//        }
//    }


    companion object {
        const val EXTRA_BIMBINGAN_ID = "extra_bimbingan_id"
        const val EXTRA_NAME_MHS = "extra_name_mhs"
    }
}