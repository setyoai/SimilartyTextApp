package com.setyo.similartytextapp.ui.home.hasilseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.FragmentHasilSeminarBinding
import com.setyo.similartytextapp.databinding.FragmentJadwalSeminarBinding
import com.setyo.similartytextapp.ui.ViewModelFactory
import com.setyo.similartytextapp.ui.home.DetailSeminarViewModel

class HasilSeminarFragment : Fragment() {

    private var _binding: FragmentHasilSeminarBinding? = null
    private val binding get() = _binding!!
    private val jadwalSeminarViewModel by viewModels<DetailSeminarViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentHasilSeminarBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        binding.toolbarHasilSeminar.imageViewBack.setOnClickListener {
            view.findNavController().navigate(R.id.action_hasilFragment_to_homeFragment)
        }
        jadwalSeminarViewModel.getDosen().observe(viewLifecycleOwner) {
            getResult(it.id_dosen)
        }
        binding.jadwalViewList.adapter = HasilSeminarAdapter(emptyList())
        showRecyclerView()

    }

    private fun getResult(id: String) {
        jadwalSeminarViewModel.getPenilaian(id)
        jadwalSeminarViewModel.penilaianResponse.observe(viewLifecycleOwner) {
            binding.jadwalViewList.adapter = HasilSeminarAdapter(it.detsemproData)
        }
    }

    private fun showLoading() {
        jadwalSeminarViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showRecyclerView() {
        binding.jadwalViewList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}