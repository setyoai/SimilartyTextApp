package com.setyo.similartytextapp.ui.home.jadwalseminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.data.remote.response.DetsemproDataItem
import com.setyo.similartytextapp.databinding.ItemRowJadwalSeminarBinding
import com.setyo.similartytextapp.databinding.ItemRowPenilaianBinding

class JadwalListAdapter(private val detSempro: List<DetsemproDataItem>):RecyclerView.Adapter<JadwalListAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowJadwalSeminarBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(detSempro: DetsemproDataItem) {
            binding.apply {
                iconCircle
                textViewDate.text = detSempro.tanggalSempro
                textViewNim.text = detSempro.nimDetsempro
                textViewName.text = detSempro.namaDetsempro
                textViewLevel.text = detSempro.levelDosen
                textViewNameRoom.text = detSempro.namaRuangan
                textViewNameTime.text = detSempro.jamSempro
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowJadwalSeminarBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(detSempro[position])
    }

    override fun getItemCount(): Int = detSempro.size

}