package com.setyo.similartytextapp.ui.home.bimbingan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.similartytextapp.data.remote.response.BimbinganResult
import com.setyo.similartytextapp.databinding.ActivityBimbinganDosen2Binding
import com.setyo.similartytextapp.ui.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class BimbinganDosen2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityBimbinganDosen2Binding
    private var getFile: File? = null
    private lateinit var factory: ViewModelFactory
    private val bimbinganViewModel: BimbinganViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBimbinganDosen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupView()
        binding.bimbinganViewList.adapter = BimbinganDosenListAdapter(emptyList())
        showRecyclerView()

    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding.apply {
            val name = intent.getStringExtra(EXTRA_NAME_DOSEN2)
            val dosbingid: String = intent.getStringExtra(EXTRA_DOSBING_ID) ?: ""
            val dosenid: String = intent.getStringExtra(EXTRA_DOSEN2_ID) ?: ""
            getResultBimbingan(dosbingid, dosenid)
            toolbarBimbingan.imageViewBack.setOnClickListener {
                val intent = Intent(this@BimbinganDosen2Activity, BimbinganActivity::class.java)
                startActivity(intent)
            }
            toolbarBimbingan.textViewPendamping.text = name
            toolbarBimbinganKet.buttonSend.setOnClickListener { sendBimbingan() }
        }
    }

    private fun sendBimbingan() {
        bimbinganViewModel.getUser().observe(this@BimbinganDosen2Activity) { userData ->
            val dosbingId: String? = intent.getStringExtra(EXTRA_DOSBING_ID)
            val mhsNim: String? = userData?.nim_mhs
            val textKet: String = binding.toolbarBimbinganKet.inputTextSendBimbingan.text.toString()
            val dosen2Id: String? = intent.getStringExtra(EXTRA_DOSEN2_ID)

            // Check for nullability before creating request bodies
            if (dosbingId != null && mhsNim != null && dosen2Id != null) {
                val dosbingIdRequestBody = dosbingId.toRequestBody("text/plain".toMediaType())
                val mhsNimRequestBody = mhsNim.toRequestBody("text/plain".toMediaType())
                val ketRequestBody = textKet.toRequestBody("text/plain".toMediaType())
                val dosen2IdRequestBody = dosen2Id.toRequestBody("text/plain".toMediaType())

                // Call the uploadResponse function with the created request bodies
                uploadResponse(
                    dosbingIdRequestBody,
                    mhsNimRequestBody,
                    ketRequestBody,
                    dosen2IdRequestBody
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

    private fun getResultBimbingan(dosbingid: String, dosenid: String) {
        bimbinganViewModel.getDataBimbingan(dosbingid, dosenid)
        bimbinganViewModel.bimbinganResponse.observe(this) {
            binding.bimbinganViewList.adapter = BimbinganDosenListAdapter(it.bimbinganData ?: emptyList())
        }
    }

    private fun showRecyclerView() {
        binding.bimbinganViewList.apply {
            layoutManager = LinearLayoutManager(this@BimbinganDosen2Activity)
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
        const val EXTRA_NAME_DOSEN2 = "extra_name_dosen2"
        const val EXTRA_DOSBING_ID = "extra_dosbing_id"
        const val EXTRA_DOSEN2_ID = "extra_dosen2_id"
    }
}