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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSkripsi.setOnClickListener {
            view.findNavController().navigate(R.id.action_pendaftaranFragment_to_skripsiFragment)
        }
        binding.buttonSeminar.setOnClickListener {
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

    private fun getResult(resultData: DafskripsiData) {
        binding.apply {
            when (resultData.statusDafskripsi) {
                "1" -> {

                }
                "2" -> {
                    buttonSeminar.visibility = View.GONE
                }
                else -> {
                    // Set a default value or handle other cases if needed
                    buttonSeminar.visibility = View.GONE
                }
            }
        }
    }


    private fun setDafSkripsi(id: String) {
        pendafraranModel.getDafSkripsi(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}