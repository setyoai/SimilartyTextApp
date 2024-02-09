package com.setyo.similartytextapp.ui.penilaian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.FragmentViewPenilaianBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class DetailPenilaianFragment : Fragment() {

    private var _binding: FragmentViewPenilaianBinding? = null
    private val binding get() = _binding!!
    private val penilaianViewModel by viewModels<PenilaianViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentViewPenilaianBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        var EXTRA_ID_SEMPRO = "extra_id_sempro"
        var EXTRA_NAME = "extra_name"
        var EXTRA_NIM = "extra_nim"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        setupView()
        binding.viewPenilaianList.adapter = DetailPenilaianListAdapter(emptyList())
        showRecyclerView()
    }

    private fun setupView() {
        val dataNim = arguments?.getString(PenilaianFragment.EXTRA_NIM)
        val dataName = arguments?.getString(PenilaianFragment.EXTRA_NAME)
        binding.apply {
            textViewNim.text = dataNim
            textViewName.text = dataName
        }
        val idSempro: String = arguments?.getString(PenilaianFragment.EXTRA_ID_SEMPRO) ?: ""
        getResult(idSempro)
        binding.toolbarViewPenilaian.imageViewBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_updatePenilaianFragment_to_penilaianFragment)
        }
    }

    private fun getResult(id: String) {
        penilaianViewModel.getDetPenilaian(id)
        penilaianViewModel.detPenilaianResponse.observe(viewLifecycleOwner) {
            binding.viewPenilaianList.adapter = DetailPenilaianListAdapter(it.detpenilaianData)
        }
    }

    private fun showLoading() {
        penilaianViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showRecyclerView() {
        binding.viewPenilaianList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}