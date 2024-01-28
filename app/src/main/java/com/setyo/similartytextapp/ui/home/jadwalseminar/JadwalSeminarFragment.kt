package com.setyo.similartytextapp.ui.home.jadwalseminar

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
import com.setyo.similartytextapp.databinding.FragmentJadwalSeminarBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class JadwalSeminarFragment : Fragment() {

    private var _binding: FragmentJadwalSeminarBinding? = null
    private val binding get() = _binding!!
    private val jadwalSeminarViewModel by viewModels<JadwalSeminarViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentJadwalSeminarBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        binding.toolbarJadwalSeminar.imageViewBack.setOnClickListener {
            view.findNavController().navigate(R.id.action_jadwalFragment_to_homeFragment)
        }
        jadwalSeminarViewModel.getDosen().observe(viewLifecycleOwner) {
            getResult(it.id_dosen)
        }
        binding.jadwalViewList.adapter = JadwalListAdapter(emptyList())
        showRecyclerView()

    }

    private fun getResult(id: String) {
        jadwalSeminarViewModel.getPenilaian(id)
        jadwalSeminarViewModel.penilaianResponse.observe(viewLifecycleOwner) {
            binding.jadwalViewList.adapter = JadwalListAdapter(it.detsemproData)
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