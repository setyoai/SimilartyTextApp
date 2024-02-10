package com.setyo.similartytextapp.ui.similarity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.FragmentDetailSimilarityBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class DetailSimilarityFragment : Fragment() {

    private var _binding: FragmentDetailSimilarityBinding? = null
    private val binding get() = _binding!!
    private val similarityModel by viewModels<SimilarityViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentDetailSimilarityBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val dataNim = arguments?.getString(EXTRA_NIM)
        val dataName = arguments?.getString(EXTRA_NAME)
        val dataTitle = arguments?.getString(EXTRA_TITLE)
        binding.apply {
            textViewName
            textViewNim
            textViewTitle
            textViewKetNim.text = dataNim
            textViewKetName.text = dataName
            textViewKetTitle.text = dataTitle
        }
        binding.toolbarDetailSimilarity.imageViewBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailSimilarityFragment_to_similarityFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_NIM = "extra_nim"
        var EXTRA_TITLE = "extra_title"
    }
}