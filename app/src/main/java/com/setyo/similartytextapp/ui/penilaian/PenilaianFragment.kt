package com.setyo.similartytextapp.ui.penilaian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.similartytextapp.databinding.FragmentPenilaianBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class PenilaianFragment : Fragment() {

    private var _binding: FragmentPenilaianBinding? = null
    private val binding get() = _binding!!
    private val penilaianViewModel by viewModels<PenilaianViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentPenilaianBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        var EXTRA_ID = "extra_id"
        var EXTRA_NAME = "extra_name"
        var EXTRA_NIM = "extra_nim"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        penilaianViewModel.getDosen().observe(viewLifecycleOwner) {
            getResult(it.id_dosen)
        }
        binding.penilaianViewList.adapter = PenilaianListAdapter(emptyList())
        showRecyclerView()
    }

    private fun getResult(id: String) {
        penilaianViewModel.getPenilaian(id)
        penilaianViewModel.penilaianResponse.observe(viewLifecycleOwner) { penilaianResponse ->
            binding.penilaianViewList.adapter = PenilaianListAdapter(penilaianResponse.detsemproData)
        }
    }

    private fun showLoading() {
        penilaianViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showRecyclerView() {
        binding.penilaianViewList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}