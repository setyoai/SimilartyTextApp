package com.setyo.similartytextapp.ui.home.dosbing

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.DetsemproDataItem
import com.setyo.similartytextapp.data.remote.response.DosbingDataItem
import com.setyo.similartytextapp.databinding.ItemRowBimbinganBinding
import com.setyo.similartytextapp.databinding.ItemRowHasilSeminarBinding

class DosbingAdapter(private val dosbing: List<DosbingDataItem>):RecyclerView.Adapter<DosbingAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowBimbinganBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(dosbing: DosbingDataItem) {
            binding.apply {
                textViewNim.text = dosbing.nimDosbing
                textViewName.text = dosbing.namaDosbing
                when(dosbing.levelDosen) {
                    "Anggota Dosbing 1" -> {
                        textViewLevel.setText("Pembimbing Utama")
                    }
                    else -> {
                        textViewLevel.setText("Pembimbing Pendamping")
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBimbinganBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(dosbing[position])
    }

    override fun getItemCount(): Int = dosbing.size

}