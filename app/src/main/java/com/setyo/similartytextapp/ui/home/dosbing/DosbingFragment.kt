package com.setyo.similartytextapp.ui.home.dosbing

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
import com.setyo.similartytextapp.databinding.FragmentBimbinganBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class DosbingFragment : Fragment() {

    private var _binding: FragmentBimbinganBinding? = null
    private val binding get() = _binding!!
    private val dosbingViewModel by viewModels<DosbingViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentBimbinganBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        binding.toolbarDosbing.imageViewBack.setOnClickListener {
            view.findNavController().navigate(R.id.action_dosbingFragment_to_homeFragment)
        }
        dosbingViewModel.getDosen().observe(viewLifecycleOwner) {
            getResult(it.id_dosen)
        }
        binding.dosbingList.adapter = DosbingAdapter(emptyList())
        showRecyclerView()

    }

    private fun getResult(id: String) {
        dosbingViewModel.getDosbing(id)
        dosbingViewModel.dosbingResponse.observe(viewLifecycleOwner) {
            binding.dosbingList.adapter = DosbingAdapter(it.dosbingData)
        }
    }

    private fun showLoading() {
        dosbingViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showRecyclerView() {
        binding.dosbingList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}