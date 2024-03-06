package com.setyo.similartytextapp.ui.similarity

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.TopThreeTitlesItem
import com.setyo.similartytextapp.databinding.ItemRowSimilartyBinding
import com.setyo.similartytextapp.ui.penilaian.PenilaianFragment

class SimilarityAdapter(private val similarity: List<TopThreeTitlesItem>):RecyclerView.Adapter<SimilarityAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowSimilartyBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(similarity: TopThreeTitlesItem) {
            binding.apply {
                textViewNumber.text = similarity.nomerUrut + ". "
                val combinedText = "${similarity.judul} <font color='#000000'><b>(${similarity.persentaseKesamaan})</b></font>"
                // Menetapkan teks ke TextView dengan format HTML
                textViewTitle.text = Html.fromHtml(combinedText)
            }
            binding.textViewTitle.setOnClickListener {
                val mBundle = Bundle()
                mBundle.putString(DetailSimilarityFragment.EXTRA_NIM, similarity.nimMhs)
                mBundle.putString(DetailSimilarityFragment.EXTRA_NAME, similarity.namaMhs)
                mBundle.putString(DetailSimilarityFragment.EXTRA_TITLE, similarity.judul)
                mBundle.putString(DetailSimilarityFragment.EXTRA_DOSEN1_ID, similarity.dosen1Dosbing)
                mBundle.putString(DetailSimilarityFragment.EXTRA_DOSEN2_ID, similarity.dosen2Dosbing)
                it.findNavController().navigate(R.id.action_similarityFragment_to_detailSimilarityFragment, mBundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowSimilartyBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(similarity[position])
    }

    override fun getItemCount(): Int = similarity.size

}