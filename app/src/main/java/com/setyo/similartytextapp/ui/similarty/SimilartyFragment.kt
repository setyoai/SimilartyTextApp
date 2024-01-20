package com.setyo.similartytextapp.ui.similarty

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.databinding.FragmentPlagiarismBinding
import com.setyo.similartytextapp.ui.ViewModelFactory

class SimilartyFragment : Fragment() {

    private var _binding: FragmentPlagiarismBinding? = null
    private val binding get() = _binding!!

    private val similartyViewModel by viewModels<SimilartyViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentPlagiarismBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        similartyViewModel.judulList.observe(viewLifecycleOwner) {
            // Handle LiveData changes or trigger relevant functions
            calculateResult()
        }

        binding.apply {
            buttonCalculate.setOnClickListener {
                // Ensure data is fetched before calculating the result
                similartyViewModel.getTitle()
            }
        }
    }

    private fun calculateResult() {
        val str1 = binding.inputTextTitle.text.toString().uppercase().replace(" ", "")
        val judulList = similartyViewModel.judulList.value

        if (judulList != null) {
            val ratcliff = AlgorithmRatcliffObershelp()

            // List to store similarity scores and corresponding indices
            val similarityScores = mutableListOf<Triple<Int, Double, Double>>()

            for (i in judulList.indices) {
                val similarity = ratcliff.similarity(
                    str1,
                    judulList[i].judulSkripsi.uppercase().replace(" ", "")
                )
                similarityScores.add(Triple(i, similarity, similarity * 100))
            }

            // Sort similarity scores in descending order
            similarityScores.sortByDescending { it.second }

            // Get the top 3 theses with the highest similarity
            val topThreeTheses = similarityScores.take(3)
                .map { judulList[it.first].judulSkripsi to it.third }

            // Get the most similar thesis
            val mostSimilarThesis = similarityScores.firstOrNull()
            val mostSimilarTitle = mostSimilarThesis?.let {
                judulList[it.first].judulSkripsi to it.third
            }

            // Display the top three theses
            val result = topThreeTheses.joinToString(", ") {
                "${it.first} (${"%.2f".format(it.second)}%)"
            }
            val mostSimilarResult = mostSimilarTitle?.let {
                "Judul paling mirip: ${it.first} (${String.format("%.2f", it.second)}%)"
            }

            Log.d(TAG, "Most similar result: $mostSimilarResult")
            binding.textViewSimilar.text = getString(R.string.top_three_title, result)
            binding.textViewResult.text = mostSimilarResult
        }
    }


//    private fun showLoading() {
//       SimilartyViewModelModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
//            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "Similarty"
    }

}