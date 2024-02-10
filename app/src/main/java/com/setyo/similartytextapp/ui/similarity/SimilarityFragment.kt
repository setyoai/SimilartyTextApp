package com.setyo.similartytextapp.ui.similarity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.similartytextapp.databinding.FragmentSimilarityBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class SimilarityFragment : Fragment() {

    private var _binding: FragmentSimilarityBinding? = null
    private val binding get() = _binding!!
    private val similarityModel by viewModels<SimilarityViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentSimilarityBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.similartyList.adapter = SimilarityAdapter(emptyList())
        setupView()
    }

    private fun setupView() {
        binding.buttonCalculate.setOnClickListener {
            calculateResult()
        }
        showRecyclerView()
    }

    private fun calculateResult() {
        val str1 = binding.inputTextTitle.text.toString()
        getResult(str1)
    }
    private fun getResult(judul: String) {
        similarityModel.getSimilarty(judul)
        similarityModel.similartyResponse.observe(viewLifecycleOwner) {
            binding.similartyList.adapter = SimilarityAdapter(it.topThreeTitles)
        }
    }

    private fun showRecyclerView() {
        binding.similartyList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun showLoading() {
        similarityModel .isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}