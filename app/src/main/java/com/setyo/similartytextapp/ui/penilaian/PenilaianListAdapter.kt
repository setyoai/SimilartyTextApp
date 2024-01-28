package com.setyo.similartytextapp.ui.penilaian

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.DetsemproDataItem
import com.setyo.similartytextapp.databinding.ItemRowPenilaianBinding

class PenilaianListAdapter(private val detSempro: List<DetsemproDataItem>):RecyclerView.Adapter<PenilaianListAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowPenilaianBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(detSempro: DetsemproDataItem) {
            binding.apply {
                textViewNim.text = detSempro.nimDetsempro
                textViewName.text = detSempro.namaDetsempro
                textViewNameRoom.text = detSempro.namaRuangan
                textViewNameTime.text = detSempro.jamSempro
                textViewNameDate.text = detSempro.tanggalSempro
            }
            binding.fabPenilaianSempro.setOnClickListener {
                val mBundle = Bundle()
                mBundle.putString(PenilaianFragment.EXTRA_ID, detSempro.idDetSempro)
                mBundle.putString(PenilaianFragment.EXTRA_NIM, detSempro.nimDetsempro)
                mBundle.putString(PenilaianFragment.EXTRA_NAME, detSempro.namaDetsempro)
                mBundle.putString(PenilaianFragment.EXTRA_TITLE, detSempro.juduldafsempro)
                mBundle.putString(PenilaianFragment.EXTRA_LEVEL, detSempro.levelDosen)
                it.findNavController().navigate(R.id.action_penilaianFragment_to_updatePenilaianFragment, mBundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowPenilaianBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(detSempro[position])
    }

    override fun getItemCount(): Int = detSempro.size

}