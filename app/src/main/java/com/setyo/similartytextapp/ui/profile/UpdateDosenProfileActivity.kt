package com.setyo.similartytextapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.setyo.similartytextapp.data.remote.response.DosenResponse
import com.setyo.similartytextapp.databinding.ActivityUpdateDosenProfileBinding
import com.setyo.similartytextapp.ui.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDosenProfileActivity : AppCompatActivity() {


    private lateinit var binding : ActivityUpdateDosenProfileBinding
    private lateinit var factory: ViewModelFactory
    private val profileViewModel: ProfileViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDosenProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupUser()
        updateUser()
        setupView()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding.toolbarUpdateProfile.imageViewBack.setOnClickListener {
          finish()
        }
    }

    private fun setupUser() {
        profileViewModel.getDosen().observe(this@UpdateDosenProfileActivity) {
            getDetailDosen(it.id_dosen)
        }
    }

    private fun getDetailDosen(id: String) {
        val client = profileViewModel.getApiService().getDosen(id)
        client.enqueue(object : Callback<DosenResponse> {
            override fun onResponse(
                call: Call<DosenResponse>,
                response: Response<DosenResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        with(binding){
                            inputTextId.setText(it.dosenData.idDosen.orEmpty())
                            inputTextUsername.setText(it.dosenData.nidnDosen.orEmpty())
                            inputTextName.setText(it.dosenData.namaDosen.orEmpty())
                            inputTextAddress.setText(it.dosenData.alamatDosen.orEmpty())
                            inputTextNoHp.setText(it.dosenData.nohpDosen.orEmpty())
                            inputTextEmail.setText(it.dosenData.emailDosen.orEmpty())
                        }
                    }
                } else {
//                    Log.e(UserRepository.TAG,"onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DosenResponse>, t: Throwable) {
//                Log.e(UserRepository.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    private fun updateUser() {
        binding.apply {
            buttonSave.setOnClickListener {
                profileViewModel.getUser().observe(this@UpdateDosenProfileActivity) {
                    val id = inputTextId.text.toString()
                    val username = inputTextUsername.text.toString()
                    val password = inputTextPassword.text.toString()
                    val name = inputTextName.text.toString()
                    val address = inputTextAddress.text.toString()
                    val nohp = inputTextNoHp.text.toString()
                    val email = inputTextEmail.text.toString()
                    updateResponse(id, username ,password, name, address, nohp, email)
                }
            }
        }
    }

    private fun updateResponse(
        id: String, username: String, password: String, name: String, address: String, nohp: String,
        email: String
    ) {
        profileViewModel.updateUserData( id, username, password, name, address, nohp, email)
        profileViewModel.updateUserResponse.observe(this@UpdateDosenProfileActivity) {
            if (!it.error) {
                showToast()
                finish()
            }
        }
    }

    private fun showToast() {
        profileViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}