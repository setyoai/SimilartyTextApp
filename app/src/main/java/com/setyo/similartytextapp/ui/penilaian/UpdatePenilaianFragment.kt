package com.setyo.similartytextapp.ui.penilaian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.FragmentUpdatePenilaianBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class UpdatePenilaianFragment : Fragment() {

    private var _binding: FragmentUpdatePenilaianBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: ViewModelFactory
    private val penilaianViewModel: PenilaianViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdatePenilaianBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupView()
        setupUser()
        updatePenilaian()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(requireContext())
    }

    private fun setupView() {
        binding.toolbarUpdatePenilaian.imageViewBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_updatePenilaianFragment_to_penilaianFragment)
        }
    }

    private fun setupUser() {
        val dataNim = arguments?.getString(PenilaianFragment.EXTRA_NIM)
        val dataName = arguments?.getString(PenilaianFragment.EXTRA_NAME)
        val dataTitle = arguments?.getString(PenilaianFragment.EXTRA_TITLE)
        when (arguments?.getString(PenilaianFragment.EXTRA_LEVEL)) {
            "Anggota Penguji 1" -> {
                binding.toolbarInputPenilaian.textViewStatus.visibility = View.GONE
                binding.toolbarInputPenilaian.radioGroupStatus.visibility = View.GONE
                binding.toolbarInputPenilaian.radioGroupHasil.visibility = View.GONE
                binding.toolbarInputPenilaian.textViewHasil.visibility = View.GONE
            }
            "Anggota Penguji 2" -> {
                binding.toolbarInputPenilaian.textViewStatus.visibility = View.GONE
                binding.toolbarInputPenilaian.radioGroupStatus.visibility = View.GONE
                binding.toolbarInputPenilaian.radioGroupHasil.visibility = View.GONE
                binding.toolbarInputPenilaian.textViewHasil.visibility = View.GONE
            }
        }

        binding.textViewNim.text = dataNim
        binding.textViewName.text = dataName
        binding.textViewJudul.text = dataTitle
    }

    private fun updatePenilaian() {
        binding.apply {
            buttonSave.setOnClickListener {
                penilaianViewModel.penilaianResponse.observe(viewLifecycleOwner) {
                    val dataId = arguments?.getString(PenilaianFragment.EXTRA_ID)
                    val id = dataId.toString()
                    val judul = toolbarInputPenilaian.inputTextJudul.text.toString()
                    val latarBelakang = toolbarInputPenilaian.inputTextLatar.text.toString()
                    val rumusanMasalah = toolbarInputPenilaian.inputTextRumusan.text.toString()
                    val batasanMasalah = toolbarInputPenilaian.inputTextBatasan.text.toString()
                    val tujuan = toolbarInputPenilaian.inputTextTujuan.text.toString()
                    val manfaat = toolbarInputPenilaian.inputTextManfaat.text.toString()
                    val tinjauanPustaka = toolbarInputPenilaian.inputTextTinjauan.text.toString()
                    val metodologi = toolbarInputPenilaian.inputTextMetodologi.text.toString()
                    val kerangkaPemikiran = toolbarInputPenilaian.inputTextKerangka.text.toString()
                    val jadwalKegiatan = toolbarInputPenilaian.inputTextJadwal.text.toString()
                    val riwayatPenelitian = toolbarInputPenilaian.inputTextRiwayat.text.toString()
                    val daftarPustaka = toolbarInputPenilaian.inputTextDaftar.text.toString()
                    toolbarInputPenilaian.textViewStatus.text
                    toolbarInputPenilaian.textViewHasil.text
                    // Get the selected radio button's text from radioGroupStatus
                    val selectedStatusId = toolbarInputPenilaian.radioGroupStatus.checkedRadioButtonId
                    val statusRadioButton = toolbarInputPenilaian.radioGroupStatus.findViewById<RadioButton>(selectedStatusId)
                    val status = statusRadioButton?.text.toString()
                    // Get the selected radio button's text from radioGroupHasil
                    val selectedHasilId = toolbarInputPenilaian.radioGroupHasil.checkedRadioButtonId
                    val hasilRadioButton = toolbarInputPenilaian.radioGroupHasil.findViewById<RadioButton>(selectedHasilId)
                    val hasil = hasilRadioButton?.text.toString()
                    updateResponse(
                        id, judul, latarBelakang, rumusanMasalah, batasanMasalah, tujuan, manfaat,
                        tinjauanPustaka, metodologi, kerangkaPemikiran, jadwalKegiatan, riwayatPenelitian,
                        daftarPustaka, status, hasil
                    )
                }
            }
        }
    }

    private fun updateResponse(
        id: String,
        judul: String,
        latarBelakang: String,
        rumusanMasalah: String,
        batasanMasalah: String,
        tujuan: String,
        manfaat: String,
        tinjauanPustaka: String,
        metodologi: String,
        kerangkaPemikiran: String,
        jadwalKegiatan: String,
        riwayatPenelitian: String,
        daftarPustaka: String,
        status: String,
        hasil: String
    ) {
        penilaianViewModel.getUpdatePenilaain(
            id, judul, latarBelakang, rumusanMasalah, batasanMasalah, tujuan, manfaat,
            tinjauanPustaka, metodologi, kerangkaPemikiran, jadwalKegiatan, riwayatPenelitian,
            daftarPustaka, status, hasil
        )
        penilaianViewModel.updatePenilaianResponse.observe(viewLifecycleOwner) {
            if (!it.error) {
                showToast()
                findNavController().navigate(R.id.action_updatePenilaianFragment_to_penilaianFragment)
            }
        }
    }

    private fun showToast() {
        penilaianViewModel.textToast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}