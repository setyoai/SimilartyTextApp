package com.setyo.similartytextapp.ui.home.jadwalseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.FragmentDetailJadwalSeminarBinding

class DetailJadwalSeminarFragment : Fragment() {

    private var _binding: FragmentDetailJadwalSeminarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentDetailJadwalSeminarBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val dataNim = arguments?.getString(EXTRA_NIM)
        val dataName = arguments?.getString(EXTRA_NAME)
        val dataTitle = arguments?.getString(EXTRA_TITLE)
        val dataKetuaPenguji = arguments?.getString(EXTRA_KETUAPENGUJI_ID)
        val dataPenguji1 = arguments?.getString(EXTRA_ANGGOTAPENGUJI1_ID)
        val dataPenguji2 = arguments?.getString(EXTRA_ANGGOTAPENGUJI2_ID)
        val dataRuangan = arguments?.getString(EXTRA_RUANGAN)
        val dataTanggal = arguments?.getString(EXTRA_TANGGAL)
        val dataJam = arguments?.getString(EXTRA_JAM)
            binding.apply {
                textViewName
                textViewNim
                textViewTitle
                textViewKetNim.text = dataNim
                textViewKetName.text = dataName
                textViewKetTitle.text = dataTitle

                textViewKetKetuaPenguji
                textViewPenguji1
                textViewPenguji2
                textViewKetKetuaPenguji.text = dataKetuaPenguji
                textViewKetPenguji1.text = dataPenguji1
                textViewKetPenguji2.text = dataPenguji2

                textViewKetRuangan.text = dataRuangan
                textViewTanggal.text = dataTanggal
                textViewKetJam.text = dataJam

            }

            binding.toolbarDetailJadwal.imageViewBack.setOnClickListener {
                it.findNavController().navigate(R.id.action_detailJadwalSeminarFragment_to_jadwalSeminarFragment)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_NIM = "extra_nim"
        var EXTRA_TITLE = "extra_title"
        var EXTRA_KETUAPENGUJI_ID = "extra_ketua_penguji1_id"
        var EXTRA_ANGGOTAPENGUJI1_ID = "extra_anggota_penguji1_id"
        var EXTRA_ANGGOTAPENGUJI2_ID = "extra_anggota_penguji2_id"
        var EXTRA_RUANGAN = "extra_ruangan"
        var EXTRA_TANGGAL = "extra_tanggal"
        var EXTRA_JAM = "extra_jam"
    }
}