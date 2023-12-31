package com.setyo.similartytextapp.ui.plagiarism

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.setyo.similartytextapp.databinding.FragmentPlagiarismBinding

class PlagiarismFragment : Fragment() {

    private var _binding: FragmentPlagiarismBinding? = null
    private val binding get() = _binding!!
//    private val historyViewModel by viewModels<HistoryViewModel> {
//        ViewModelFactory.getInstance(requireContext())
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentPlagiarismBinding.inflate(inflater, container, false)

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        showLoading()
//        historyViewModel.getUser().observe(viewLifecycleOwner) {
//            getResult(it.token)
//        }
//
//        binding.recyclerViewHistory.adapter = HistoryListAdapter(emptyList())
//        showRecyclerView()
//    }
//
//    private fun getResult(token: String) {
//        historyViewModel.getHistory(token)
//        historyViewModel.historyResponse.observe(viewLifecycleOwner) {
//            binding.recyclerViewHistory.adapter = HistoryListAdapter(it)
//        }
//
//    }
//
//    private fun showLoading() {
//       historyViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
//            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//        }
//    }
//
//    private fun showRecyclerView() {
//        binding.recyclerViewHistory.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            setHasFixedSize(true)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

}