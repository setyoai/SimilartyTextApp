package com.setyo.similartytextapp.ui.pendaftaran

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.DafsemproData
import com.setyo.similartytextapp.data.remote.response.DafskripsiData
import com.setyo.similartytextapp.data.remote.response.UploadResultSkripsi
import com.setyo.similartytextapp.databinding.FragmentPendaftaranBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class PendaftaranFragment : Fragment() {

    private var _binding: FragmentPendaftaranBinding? = null
    private val binding get() = _binding!!
    private val pendafraranModel by viewModels<PendaftaranViewModel>{
        ViewModelFactory.getInstance(requireContext())
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentPendaftaranBinding.inflate(inflater, container, false)

        setDafSkripsi()
        setDafSempro()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewSkripsi.setOnClickListener {
            view.findNavController().navigate(R.id.action_pendaftaranFragment_to_skripsiFragment)
        }
        binding.cardViewSeminar.setOnClickListener {
            view.findNavController().navigate(R.id.action_pendaftaranFragment_to_seminarFragment)
        }
    }

    private fun setDafSkripsi(){
        pendafraranModel.getUser().observe(viewLifecycleOwner) {
            setDafSkripsi(it.nim_mhs)
        }
        pendafraranModel.resultDafSkripsiResponse.observe(viewLifecycleOwner) {
            val dafskripsiData = it.dafskripsiData
            getResult(dafskripsiData)
        }
    }

    private fun setDafSempro(){
        pendafraranModel.resultDafSkripsiResponse.observe(viewLifecycleOwner) {
            setDafSempro(it.dafskripsiData.idDafskripsi)
        }
        pendafraranModel.dafsemResponse.observe(viewLifecycleOwner) {
            val dafsemproData= it.dafsemproData
            getResultSempro(dafsemproData)
        }
    }

    private fun getResult(resultData: DafskripsiData) {
        binding.apply {
            textViewResultSkripsi.text = resultData.statusDafskripsi
            when (resultData.statusDafskripsi) {
                "1" -> {
                    textViewResultSkripsi.text = "Berhasil"
                    textViewResultSkripsi.setTextColor(Color.GREEN) // Set the color to green for success
                }
                "2" -> {
                    textViewResultSkripsi.text = "Ditolak"
                    textViewResultSkripsi.setTextColor(Color.RED) // Set the color to red for rejection
                    cardViewSeminar.visibility = View.GONE
                    textViewStatusKetSeminar.visibility = View.GONE
                    textViewStatusSeminar.visibility = View.GONE
                    textViewResultSeminar.visibility = View.GONE
                    textViewResultKetSem.visibility = View.GONE
                }
                else -> {
                    // Set a default value or handle other cases if needed
                    textViewResultSkripsi.text = getString(R.string.initial_status_waiting)
                    textViewResultSkripsi.setTextColor(Color.BLACK) // Set a default color if needed
                    cardViewSeminar.visibility = View.GONE
                    textViewStatusKetSeminar.visibility = View.GONE
                    textViewStatusSeminar.visibility = View.GONE
                    textViewResultSeminar.visibility = View.GONE
                    textViewResultKetSem.visibility = View.GONE
                }
            }
            textViewResultKet.text = resultData.keteranganDafskripsi
        }
    }

    private fun getResultSempro(dafsemproData: DafsemproData) {
        binding.apply {
            textViewResultSeminar.text = dafsemproData.statusDafsempro
            when (dafsemproData.statusDafsempro) {
                "1" -> {
                    textViewResultSeminar.text = "Berhasil"
                    textViewResultSeminar.setTextColor(Color.GREEN) // Set the color to green for success
                }
                "2" -> {
                    textViewResultSeminar.text = "Ditolak"
                    textViewResultSeminar.setTextColor(Color.RED) // Set the color to red for rejection
                }
                else -> {
                    // Set a default value or handle other cases if needed
                    textViewResultSeminar.text = getString(R.string.initial_status_waiting)
                    textViewResultSeminar.setTextColor(Color.BLACK) // Set a default color if needed
                }
            }
            textViewResultKetSem.text = dafsemproData.keteranganDafsempro
        }
    }

    private fun setDafSkripsi(id: String) {
        pendafraranModel.getDafSkripsi(id)
    }

    private fun setDafSempro(id: String) {
        pendafraranModel.getDafSempro(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}