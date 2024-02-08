package com.setyo.similartytextapp.ui.home.daftarseminar

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
import com.setyo.similartytextapp.databinding.FragmentDaftarSeminarBinding
import com.setyo.similartytextapp.ui.ViewModelFactory
import com.setyo.similartytextapp.ui.home.dosbing.DosbingViewModel

class DaftarSeminarFragment : Fragment() {

    private var _binding: FragmentDaftarSeminarBinding? = null
    private val binding get() = _binding!!
    private val detailSeminarViewModel by viewModels<DosbingViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentDaftarSeminarBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
//        binding.toolbarDaftarSeminar.imageViewBack.setOnClickListener {
//            view.findNavController().navigate(R.id.action_daftarFragment_to_homeFragment)
//        }
        detailSeminarViewModel.getDosen().observe(viewLifecycleOwner) {
            getResult(it.id_dosen)
        }
        binding.daftarSeminarViewList.adapter = DaftarSeminarAdapter(emptyList())
        showRecyclerView()

    }

    private fun getResult(id: String) {
        detailSeminarViewModel.getDosbing(id)
        detailSeminarViewModel.dosbingResponse.observe(viewLifecycleOwner) {
            binding.daftarSeminarViewList.adapter = DaftarSeminarAdapter(it.dosbingData)
        }
    }

    private fun showLoading() {
        detailSeminarViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showRecyclerView() {
        binding.daftarSeminarViewList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}