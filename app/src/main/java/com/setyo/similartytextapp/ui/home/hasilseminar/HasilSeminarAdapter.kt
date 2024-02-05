package com.setyo.similartytextapp.ui.home.hasilseminar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.data.remote.response.DetsemproDataItem
import com.setyo.similartytextapp.data.remote.response.DosbingDataItem
import com.setyo.similartytextapp.databinding.ItemRowHasilSeminarBinding

class HasilSeminarAdapter(private val detSempro: List<DosbingDataItem>):RecyclerView.Adapter<HasilSeminarAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowHasilSeminarBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(detSempro: DosbingDataItem) {
            binding.apply {
                textViewDate.text = detSempro.tanggalSempro
                textViewNim.text = detSempro.nimDosbing
                textViewName.text = detSempro.namaDosbing
//                when(detSempro.hasilSempro) {
//                    "Diterima" -> {
//                        cardViewResult.setBackgroundColor(Color.GREEN)
//                    }
//                    "Ditolak" -> {
//                        cardViewResult.setBackgroundColor(Color.RED)
//                    }
//                    else -> {
//                        cardViewResult.setBackgroundColor(Color.YELLOW)
//                    }
//                }
                if (detSempro.hasilSempro == null) {
                    textViewResult.text
                } else {
                    textViewResult.text = detSempro.hasilSempro
                }
                textViewNameRoom.text = detSempro.namaRuangan
                textViewNameTime.text = detSempro.jamSempro
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowHasilSeminarBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(detSempro[position])
    }

    override fun getItemCount(): Int = detSempro.size

}