package com.setyo.similartytextapp.ui.skripsi

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.setyo.similartytextapp.databinding.FragmentSkripsiBinding
import com.setyo.similartytextapp.helper.uriToFile
import com.setyo.similartytextapp.ui.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class SkripsiFragment : Fragment() {
    private var _binding: FragmentSkripsiBinding? = null
    private var getFileKrs: File? = null
    private var getFileTranskrip: File? = null
    private var getFileSlip: File? = null
    private val binding get() = _binding!!
    private val dafsemproModel by viewModels<SkripsiViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentSkripsiBinding.inflate(inflater, container, false)

        return binding.root

    }

    private val cropKrsResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropKrsResult(result, binding.imageViewKrs)
    }

    private val cropTranskripResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropTranskripResult(result, binding.imageViewTranskripNilai)
    }

    private val cropSlipResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropSlipResult(result, binding.imageViewSlip)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            imageViewKrs.setOnClickListener { openGallery(cropKrsResultLauncher) }
            imageViewTranskripNilai.setOnClickListener { openGallery(cropTranskripResultLauncher) }
            imageViewSlip.setOnClickListener { openGallery(cropSlipResultLauncher) }
            buttonUpload.setOnClickListener { uploadFile() }
        }

        if (!allPermissionGranted()) {
            ActivityCompat.requestPermissions(
                this.requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun openGallery(cropLauncher: ActivityResultLauncher<CropImageContractOptions>) {
        cropLauncher.launch(
            options {
                setGuidelines(CropImageView.Guidelines.ON)
            }
        )
    }

    private fun handleCropKrsResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFileKrs = localFile
            imageView.setImageURI(selectedImage)
        } else {
            Toast.makeText(requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleCropTranskripResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFileTranskrip = localFile
            imageView.setImageURI(selectedImage)
        } else {
            Toast.makeText(requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleCropSlipResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFileSlip = localFile
            imageView.setImageURI(selectedImage)
        } else {
            Toast.makeText(requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadFile() {
        // Show loading indicator while the files are being uploaded
//        showLoading()

        // Observe user data using a ViewModel (dafsemproModel)
        dafsemproModel.getUser().observe(viewLifecycleOwner) {
            // Check if the getFile variable is not null
            if (
                getFileKrs != null && getFileTranskrip != null && getFileSlip != null
            ) {
                // Get the original file
                val originalFileKrs = getFileKrs as File
                val originalFileTranskrip = getFileTranskrip as File
                val originalFileSlip = getFileSlip as File

//                val transkripFile = reduceImageSize(originalFile, requireContext())
//                val pengesahanFile = reduceImageSize(originalFile, requireContext())
//                val bukuBimbinganFile = reduceImageSize(originalFile, requireContext())
//                val kwKomputerFile = reduceImageSize(originalFile, requireContext())
//                val kwInggrisFile = reduceImageSize(originalFile, requireContext())


                val krsMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileKrs, "krs_dafskripsi"
                )
                // Create MultipartBody.Part for each file to be uploaded
                val transkripMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileTranskrip, "transkrip_dafskripsi"
                )
                val slipPembayaranMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileSlip, "slippembayaran_dafskripsi"
                )

                val nim = it.nim_mhs.toRequestBody("text/plain".toMediaType())

                // Call the uploadResponse function with the created MultipartBody.Parts
                uploadResponse(
                    nim,
                    krsMultipart,
                    transkripMultipart,
                    slipPembayaranMultipart,
                )
            }
        }
    }

    // Function to create MultipartBody.Part for a file
    private fun createFormDataPart(file: File, paramName: String): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            paramName,
            file.name,
            file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        )
    }


    private fun uploadResponse(
        nim: RequestBody,
        krs: MultipartBody.Part,
        transkripNilai: MultipartBody.Part,
        slipPembayaran: MultipartBody.Part,

    ) {
        dafsemproModel.uploadFileSkripsi(nim, krs, transkripNilai, slipPembayaran)
        dafsemproModel.dafSkripsiResponse.observe(viewLifecycleOwner) {
            if (!it.error!!) {
//                moveFragment()
            }
        }
        showToast()
    }

    private fun moveFragment() {

    }

    private fun showToast() {
        dafsemproModel.textToast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading() {
        dafsemproModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10

    }

}