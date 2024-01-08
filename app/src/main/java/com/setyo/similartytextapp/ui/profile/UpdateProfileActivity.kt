package com.setyo.similartytextapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.setyo.similartytextapp.data.remote.response.UserResponse
import com.setyo.similartytextapp.data.remote.retrofit.ApiService
import com.setyo.similartytextapp.databinding.ActivityUpdateProfileBinding
import com.setyo.similartytextapp.repository.UserRepository
import com.setyo.similartytextapp.ui.Event
import com.setyo.similartytextapp.ui.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProfileActivity : AppCompatActivity() {


    private lateinit var binding : ActivityUpdateProfileBinding
    private lateinit var factory: ViewModelFactory
    private val profileViewModel: ProfileViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
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
        profileViewModel.getUser().observe(this@UpdateProfileActivity) {
            getDetailUser(it.id_mhs)
        }
    }

    private fun getDetailUser(id: String) {
        val client = profileViewModel.getApiService().getUserData(id)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        with(binding){
                            inputTextId.setText(it.userData.idMhs.orEmpty())
                            inputTextUsername.setText(it.userData.nimMhs.orEmpty())
                            inputTextName.setText(it.userData.namaMhs.orEmpty())
                            inputTextAddress.setText(it.userData.alamatMhs.orEmpty())
                        }
                    }
                } else {
//                    Log.e(UserRepository.TAG,"onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Log.e(UserRepository.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    private fun updateUser() {
        binding.apply {
            buttonSave.setOnClickListener {
                profileViewModel.getUser().observe(this@UpdateProfileActivity) {
                    val id = inputTextId.text.toString()
                    val username = inputTextUsername.text.toString()
                    val password = inputTextPassword.text.toString()
                    val name = inputTextName.text.toString()
                    val address = inputTextAddress.text.toString()
                    updateResponse(id, username ,password, name, address)
                }
            }
        }
    }

    private fun updateResponse(id: String, username: String, password: String, name: String, address: String ,) {
        profileViewModel.updateUserData( id, username, password, name, address)
        profileViewModel.updateUserResponse.observe(this@UpdateProfileActivity) {
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