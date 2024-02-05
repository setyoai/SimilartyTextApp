package com.setyo.similartytextapp.ui.home.bimbingan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.similartytextapp.data.remote.response.BimbinganResult
import com.setyo.similartytextapp.databinding.ActivityBimbinganDosen1Binding
import com.setyo.similartytextapp.ui.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class BimbinganMhsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBimbinganDosen1Binding
    private var getFile: File? = null
    private lateinit var factory: ViewModelFactory
    private val bimbinganViewModel: BimbinganViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBimbinganDosen1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        bimbinganViewModel.getUser().observe(this) {
            getResultBimbingan(it.nim_mhs)
        }

        binding.bimbinganViewList.adapter = BimbinganDosen1ListAdapter(emptyList())
        showRecyclerView()

    }

    private fun setupView() {
        binding.apply {
            val name = intent.getStringExtra(EXTRA_NAME_DOSEN)
            toolbarBimbingan.imageViewBack.setOnClickListener {
                val intent = Intent(this@BimbinganMhsActivity, BimbinganActivity::class.java)
                startActivity(intent)
            }
            toolbarBimbingan.textViewTitleBar.text = name
            toolbarBimbinganKet.buttonSend.setOnClickListener { sendBimbingan() }
        }
    }

    private fun sendBimbingan() {
        bimbinganViewModel.getUser().observe(this@BimbinganMhsActivity) { userData ->
            val dosbingId: String? = intent.getStringExtra(EXTRA_DOSBING_ID)
            val mhsNim: String? = userData?.nim_mhs
            val textKet: String = binding.toolbarBimbinganKet.inputTextSendBimbingan.text.toString()
            val dosen1Id: String? = intent.getStringExtra(EXTRA_DOSEN1_ID)

            // Check for nullability before creating request bodies
            if (dosbingId != null && mhsNim != null && dosen1Id != null) {
                val dosbingIdRequestBody = dosbingId.toRequestBody("text/plain".toMediaType())
                val mhsNimRequestBody = mhsNim.toRequestBody("text/plain".toMediaType())
                val ketRequestBody = textKet.toRequestBody("text/plain".toMediaType())
                val dosen1IdRequestBody = dosen1Id.toRequestBody("text/plain".toMediaType())

                // Call the uploadResponse function with the created request bodies
                uploadResponse(
                    dosbingIdRequestBody,
                    mhsNimRequestBody,
                    ketRequestBody,
                    dosen1IdRequestBody
                )
            } else {
                // Handle the case where any of the required values is null
                // You might want to show an error message or handle it according to your logic
            }
        }
    }

    private fun uploadResponse(
        dosbingid: RequestBody,
        mhsnim: RequestBody,
//        bab: RequestBody,
        ket: RequestBody,
//        file: MultipartBody.Part,
        dosenid: RequestBody
    ) {
        bimbinganViewModel.sendBimbingan(dosbingid, mhsnim, ket, dosenid)
        bimbinganViewModel.createBimbinganResponse.observe(this) {
            val bimbinganResult = it.bimbinganResult
            getBimbinganResult(bimbinganResult)
        }
        showToast()
    }

    private fun getBimbinganResult(bimbinganResult: BimbinganResult) {
        binding.apply {

        }
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun getResultBimbingan(id: String) {
        bimbinganViewModel.getDataBimbingan(id)
        bimbinganViewModel.bimbinganResponse.observe(this) {
            binding.bimbinganViewList.adapter = BimbinganDosen1ListAdapter(it.bimbinganData ?: emptyList())
        }
    }

    private fun showRecyclerView() {
        binding.bimbinganViewList.apply {
            layoutManager = LinearLayoutManager(this@BimbinganMhsActivity)
            setHasFixedSize(true)
        }
    }

    private fun showToast() {
        bimbinganViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun showLoading() {
//        bimbinganViewModel.isLoading.observe(this@BimbinganMhsActivity) { isLoading ->
//            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//        }
//    }


    companion object {
        const val EXTRA_NAME_DOSEN = "extra_name_dosen"
        const val EXTRA_DOSBING_ID = "extra_dosbing_id"
        const val EXTRA_DOSEN1_ID = "extra_dosen1_id"
    }
}