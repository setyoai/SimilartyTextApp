package com.setyo.similartytextapp.ui.penilaian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.FragmentUpdatePenilaianBinding
import com.setyo.similartytextapp.ui.ViewModelFactory


class UpdatePenilaianFragment : Fragment() {

    private var _binding: FragmentUpdatePenilaianBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: ViewModelFactory
    private val penilaianViewModel: PenilaianViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdatePenilaianBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupUser()
       updatePenilaian()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(requireContext())
    }

//    private fun setupView() {
//        binding.toolbarUpdateProfile.imageViewBack.setOnClickListener {
//            // Handle the back button click
//            requireActivity().onBackPressed()
//        }
//    }

    private fun setupUser() {

        val dataNim = arguments?.getString(PenilaianFragment.EXTRA_NIM)
        val dataName = arguments?.getString(PenilaianFragment.EXTRA_NAME)

        binding.textViewNim.text = dataNim
        binding.textViewName.text = dataName

    }

    private fun updatePenilaian() {
        binding.apply {
            buttonSave.setOnClickListener {
                penilaianViewModel.penilaianResponse.observe(viewLifecycleOwner) {
                    val dataId = arguments?.getString(PenilaianFragment.EXTRA_ID)
                    val id = dataId.toString()
                    val ketrev = inputTextKetRev.text.toString()
                    updateResponse(id, ketrev)
                }
            }
        }
    }

    private fun updateResponse(id: String, ketrev: String) {
        penilaianViewModel.getUpdatePenilaain(id, ketrev)
        penilaianViewModel.updatePenilaianResponse.observe(viewLifecycleOwner) {
            if (!it.error) {
                showToast()
                findNavController().navigate(R.id.action_updatePenilaianFragment_to_penilaianFragment)
            }
        }
    }

    private fun showToast() {
        penilaianViewModel.textToast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}