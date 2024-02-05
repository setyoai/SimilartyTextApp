package com.setyo.similartytextapp.ui.profile

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.canhub.cropper.*
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.UserData
import com.setyo.similartytextapp.data.remote.response.UserDetails
import com.setyo.similartytextapp.databinding.FragmentProfileBinding
import com.setyo.similartytextapp.helper.uriToFile
import com.setyo.similartytextapp.ui.ViewModelFactory
import java.io.File

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private var getFile: File? = null
    private val binding get() = _binding!!
    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupUser()
        setupAction()
        setupView()
//        binding.apply {
//            imageViewAvatar.setOnClickListener { openGallery() }
////            updateUser()
//        }
        if (!allPermissionGranted()) {
            ActivityCompat.requestPermissions(
                this.requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
        return binding.root
    }

    private fun setupView() {
        binding.buttonEdit.setOnClickListener {
            val intent = Intent(requireActivity(), UpdateProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupUser() {
        var userRole: String?
        profileViewModel.getUser().observe(viewLifecycleOwner) { userData ->
            userData?.let { mahasiswa ->
                userRole = mahasiswa.role
                when(userRole) {
                    "mahasiswa" -> {
                        binding.textViewName.setTextColor(Color.BLACK)
                        binding.textViewUsername.setTextColor(Color.BLACK)
                        binding.textViewNameDosen.visibility = View.GONE
                        binding.textViewUsernameDosen.visibility = View.GONE
                        profileViewModel.getUser().observe(viewLifecycleOwner) {
                            setUserData(it.id_mhs)
                        }
                        profileViewModel.userResponse.observe(viewLifecycleOwner) {
                            val userData = it.userData
                            getUserData(userData)
                        }
                    } else -> {
                    binding.textViewNameDosen.setTextColor(Color.BLACK)
                    binding.textViewUsernameDosen.setTextColor(Color.BLACK)
                        profileViewModel.getDosen().observe(viewLifecycleOwner) {
                            setUserDosen(it.id_user)
                        }
                        profileViewModel.getDosenResponse.observe(viewLifecycleOwner) {
                            val userData = it.userDetails
                            getUserDosen(userData)
                        }
                    }

                }

            }
        }
    }

    private fun getUserData(userData: UserData) {
        binding.apply {
            Glide.with(requireContext())
                .load(userData.photoMhs)
                .error(R.drawable.outline_account_circle_64)
                .into(imageViewAvatar)
           textViewName.text = userData.namaMhs
           textViewUsername.text =  userData.nimMhs
        }
    }

    private fun getUserDosen(userDosen: UserDetails) {
        binding.apply {
            Glide.with(requireContext())
                .load(userDosen.idUser)
                .error(R.drawable.outline_account_circle_64)
                .into(imageViewAvatar)
            textViewNameDosen.text = userDosen.namaDosen
            textViewUsernameDosen.text = userDosen.username
        }
    }

    private val cropFragmentResultLauncher =
        registerForActivityResult(CropImageContract()) { result ->
            if (result.isSuccessful) {
                val selectedImage = result.uriContent
                val localFile = selectedImage?.let { uriToFile(it, this.requireContext()) }

                getFile = localFile
                binding.imageViewAvatar.setImageURI(selectedImage)
            } else {
                Toast.makeText(this.requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
            }
        }

    private fun openGallery() {
        cropFragmentResultLauncher.launch(
            options {
                setGuidelines(CropImageView.Guidelines.ON)
                setAspectRatio(1,1)
                setFixAspectRatio(true)
            }
        )
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun setUserData(id: String) {
        profileViewModel.getUserData(id)
    }

    private fun setUserDosen(id: String) {
        profileViewModel.getUserDosen(id)
    }

//    private fun updateUser() {
//        profileViewModel.getUser().observe(viewLifecycleOwner) { user ->
//            if (getFile != null) {
//                val file = reduceImageSize(getFile as File)
//                val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
//                val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
//                    "avatar_image",
//                    file.name,
//                    requestImageFile
//                )
//                updateResponse(imageMultipart)
//            }
//        }
//    }
//
//    private fun updateResponse(avatar_image: MultipartBody.Part) {
//        profileViewModel.updateUser(token, avatar_image)
//        profileViewModel.updateUserResponse.observe(viewLifecycleOwner) {
//            if (!it.error) {
//                showToast()
//            }
//        }
//    }
//
    private fun setupAction() {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> {
                    // Handle logout menu item click
                    profileViewModel.logoutUser()
                    true
                }
                // Handle other menu items if needed
                else -> false
            }
        }
    }

    private fun showToast() {
        profileViewModel.textToast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

        companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}