package com.setyo.similartytextapp.ui.home.hasilseminarmahasiswa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.SemproMhsData
import com.setyo.similartytextapp.databinding.FragmentHasilSeminarMahasiswaBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class HasilSeminarMahasiswaFragment : Fragment() {

    private var _binding: FragmentHasilSeminarMahasiswaBinding? = null
    private val binding get() = _binding!!
    private val hasilSeminarViewModel by viewModels<HasilSeminarViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentHasilSeminarMahasiswaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarHasilSeminar.imageViewBack.setOnClickListener {
            view.findNavController().navigate(R.id.action_hasilSeminarMhs_to_homeFragment)
        }
        showLoading()
        setDafSkripsi()
        setDafSempro()
        setResultSemproMhs()
    }

    private fun setDafSkripsi(){
        hasilSeminarViewModel.getUser().observe(viewLifecycleOwner) {
            setDafSkripsi(it.nim_mhs)
        }
    }

    private fun setDafSempro(){
        hasilSeminarViewModel.resultDafSkripsiResponse.observe(viewLifecycleOwner) {
            setDafSempro(it.dafskripsiData.idDafskripsi)
        }
    }

    private fun setResultSemproMhs(){
        hasilSeminarViewModel.dafsemResponse.observe(viewLifecycleOwner) {
            setResultSemproMhs(it.dafsemproData.idDafsempro)
        }
        hasilSeminarViewModel.resultSeminarMhsResponse.observe(viewLifecycleOwner) {
            val semproMhsData= it.semproMhsData
            getResultSemproMhs(semproMhsData)
        }
    }

    private fun getResultSemproMhs(semproMhsData: SemproMhsData) {
        binding.apply {
            textViewNim.text = semproMhsData.nimSempro
            textViewName.text = semproMhsData.namaSempro
            textViewDate.text = semproMhsData.jamSempro
            textViewNameRoom.text = semproMhsData.namaRuangan
            textViewResult.text = semproMhsData.hasilSempro
        }
    }

    private fun setDafSkripsi(id: String) {
        hasilSeminarViewModel.getDafSkripsi(id)
    }

    private fun setDafSempro(id: String) {
        hasilSeminarViewModel.getDafSempro(id)
    }

    private fun setResultSemproMhs(id: String) {
        hasilSeminarViewModel.getResultSeminarMhs(id)
    }

    private fun showLoading() {
        hasilSeminarViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}