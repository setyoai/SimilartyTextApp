package com.setyo.similartytextapp.ui.seminar

import android.content.pm.PackageManager
import android.graphics.Color
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
import androidx.navigation.findNavController
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.DafskripsiData
import com.setyo.similartytextapp.databinding.FragmentSeminarBinding
import com.setyo.similartytextapp.helper.uriToFile
import com.setyo.similartytextapp.ui.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class SeminarFragment : Fragment() {
    private var _binding: FragmentSeminarBinding? = null
    private var getFileTranskrip: File? = null
    private var getFilePengesahan: File? = null
    private var getFileBimbingan: File? = null
    private var getFileKomputer: File? = null
    private var getFileInggris: File? = null
    private var getFileKewirausahaan: File? = null
    private var getFileSlip: File? = null
    private var getFilePlagiasi: File? = null
    private val binding get() = _binding!!
    private val dafsemproModel by viewModels<SeminarViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentSeminarBinding.inflate(inflater, container, false)

        return binding.root

    }

    private val cropTranskripResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropTranskripResult(result, binding.imageViewTranskripNilai)
    }

    private val cropPengesahanResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropPengesahanResult(result, binding.imageViewPengesahan)
    }

    private val cropBimbinganResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropBimbinganResult(result, binding.imageViewBimbingan)
    }

    private val cropKomputerResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropKomputerResult(result, binding.imageViewKomputer)
    }

    private val cropInggrisResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropInggrisResult(result, binding.imageViewInggris)
    }

    private val cropKewirausahaanResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropKewirausahaanResult(result, binding.imageViewKewirausahaan)
    }

    private val cropSlipResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropSlipResult(result, binding.imageViewSlip)
    }

    private val cropPlagiasiResultLauncher = registerForActivityResult(CropImageContract()) { result ->
        handleCropPlagiasiResult(result, binding.imageViewPlagiasi)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarSeminar.imageViewBack.setOnClickListener {
            view.findNavController().navigate(R.id.action_seminarFragment_to_pendaftaranFragment)
        }
        binding.apply {
            imageViewTranskripNilai.setOnClickListener { openGallery(cropTranskripResultLauncher) }
            imageViewPengesahan.setOnClickListener { openGallery(cropPengesahanResultLauncher) }
            imageViewBimbingan.setOnClickListener { openGallery(cropBimbinganResultLauncher) }
            imageViewKomputer.setOnClickListener { openGallery(cropKomputerResultLauncher) }
            imageViewInggris.setOnClickListener { openGallery(cropInggrisResultLauncher) }
            imageViewKewirausahaan.setOnClickListener { openGallery(cropKewirausahaanResultLauncher) }
            imageViewSlip.setOnClickListener { openGallery(cropSlipResultLauncher) }
            imageViewPlagiasi.setOnClickListener { openGallery(cropPlagiasiResultLauncher) }
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

    private fun handleCropPengesahanResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFilePengesahan = localFile
            imageView.setImageURI(selectedImage)
        } else {
            Toast.makeText(requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleCropBimbinganResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFileBimbingan = localFile
            imageView.setImageURI(selectedImage)
        } else {
            Toast.makeText(requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleCropKomputerResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFileKomputer = localFile
            imageView.setImageURI(selectedImage)
        } else {
            Toast.makeText(requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleCropInggrisResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFileInggris = localFile
            imageView.setImageURI(selectedImage)
        } else {
            Toast.makeText(requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleCropKewirausahaanResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFileKewirausahaan = localFile
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

    private fun handleCropPlagiasiResult(result: CropImageView.CropResult, imageView: ImageView) {
        if (result.isSuccessful) {
            val selectedImage = result.uriContent
            val localFile = selectedImage?.let { uriToFile(it, requireContext()) }

            getFilePlagiasi = localFile
            imageView.setImageURI(selectedImage)
        } else {
            Toast.makeText(requireContext(), "Gagal mengambil Gambar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadFile() {
        // Show loading indicator while the files are being uploaded
        showLoading()

        // Observe user data using a ViewModel (dafsemproModel)
        dafsemproModel.getUser().observe(viewLifecycleOwner) {
            setDafSkripsi(it.nim_mhs)
        }
        dafsemproModel.resultDafSkripsiResponse.observe(viewLifecycleOwner) {
            // Check if the getFile variable is not null
            if (
                getFileTranskrip != null && getFilePengesahan != null &&
                getFileBimbingan != null && getFileKomputer != null &&
                getFileInggris != null && getFileKewirausahaan != null &&
                getFileSlip != null && getFilePlagiasi != null
            ) {
                // Get the original file
                val originalFileTranskrip = getFileTranskrip as File
                val originalFilePengesahan = getFilePengesahan as File
                val originalFileBimbingan = getFileBimbingan as File
                val originalFileKomputer = getFileKomputer as File
                val originalFileInggris = getFileInggris as File
                val originalFileKewirausahaan = getFileKewirausahaan as File
                val originalFileSlip = getFileSlip as File
                val originalFilePlagiasi = getFilePlagiasi as File

//                val transkripFile = reduceImageSize(originalFile, requireContext())
//                val pengesahanFile = reduceImageSize(originalFile, requireContext())
//                val bukuBimbinganFile = reduceImageSize(originalFile, requireContext())
//                val kwKomputerFile = reduceImageSize(originalFile, requireContext())
//                val kwInggrisFile = reduceImageSize(originalFile, requireContext())

                // Create MultipartBody.Part for each file to be uploaded
                val transkripMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileTranskrip, "transkrip_dafsempro"
                )
                val pengesahanMultipart: MultipartBody.Part = createFormDataPart(
                    originalFilePengesahan, "pengesahan_dafsempro"
                )
                val bukuBimbinganMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileBimbingan, "bukubimbingan_dafsempro"
                )
                val kwKomputerMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileKomputer, "kwkomputer_dafsempro"
                )
                val kwInggrisMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileInggris, "kwinggris_dafsempro"
                )
                val kwKewirausahaanMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileKewirausahaan, "kwkwu_dafsempro"
                )
                val slipPembayaranMultipart: MultipartBody.Part = createFormDataPart(
                    originalFileSlip, "slippembayaran_dafsempro"
                )
                val plagiasiMultipart: MultipartBody.Part = createFormDataPart(
                    originalFilePlagiasi, "plagiasi_dafsempro"
                )
                val id = it.dafskripsiData.idDafskripsi.toRequestBody("text/plain".toMediaType())
                val title = binding.inputTextTitle.text.toString()
                val judul = title.toRequestBody("text/plain".toMediaType())

                // Call the uploadResponse function with the created MultipartBody.Parts
                uploadResponse(
                    id,
                    judul,
                    transkripMultipart,
                    pengesahanMultipart,
                    bukuBimbinganMultipart,
                    kwKomputerMultipart,
                    kwInggrisMultipart,
                    kwKewirausahaanMultipart,
                    slipPembayaranMultipart,
                    plagiasiMultipart,
                )
            }
        }
    }

    private fun setDafSkripsi(id: String) {
        dafsemproModel.getDafSkripsi(id)
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
        id: RequestBody,
        judul: RequestBody,
        transkripNilai: MultipartBody.Part,
        pengesahan: MultipartBody.Part,
        bukuBimbingan: MultipartBody.Part,
        kwKomputer: MultipartBody.Part,
        kwInggris: MultipartBody.Part,
        kwKewirausahaan: MultipartBody.Part,
        slipPembayaran: MultipartBody.Part,
        plagiasi: MultipartBody.Part,
    ) {
        dafsemproModel.uploadFileSeminar(
            id,
            judul,
            transkripNilai,
            pengesahan,
            bukuBimbingan,
            kwKomputer,
            kwInggris,
            kwKewirausahaan,
            slipPembayaran,
            plagiasi,
        )
        dafsemproModel.dafsemResponse.observe(viewLifecycleOwner) {
            if (!it.error!!) {
//                moveFragment()
            }
        }
        showToast()
    }

//    private fun moveFragment() {
//
//    }

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