package com.setyo.similartytextapp.ui.home.jadwalseminar

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.R
import com.setyo.similartytextapp.data.remote.response.DetsemproDataItem
import com.setyo.similartytextapp.databinding.ItemRowJadwalSeminarBinding
import com.setyo.similartytextapp.ui.similarity.DetailSimilarityFragment

class JadwalListAdapter(private val detSempro: List<DetsemproDataItem>):RecyclerView.Adapter<JadwalListAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowJadwalSeminarBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(detSempro: DetsemproDataItem) {
            binding.apply {
                iconCircle
                if (detSempro.hasilSempro != null) {
                    jadwalView.setBackgroundColor(Color.RED)
                }
                textViewDate.text = detSempro.tanggalSempro
                textViewNim.text = detSempro.nimDetsempro
                textViewName.text = detSempro.namaDetsempro
                textViewLevel.text = detSempro.levelDosen
                textViewNameRoom.text = detSempro.namaRuangan
                textViewNameTime.text = detSempro.jamSempro
            }
            binding.fabDetailJadwalSeminar.setOnClickListener {
                val mBundle = Bundle()
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_NIM, detSempro.nimDetsempro)
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_NAME, detSempro.namaDetsempro)
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_TITLE, detSempro.juduldafsempro)
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_KETUAPENGUJI_ID, detSempro.ketuaPenguji)
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_ANGGOTAPENGUJI1_ID, detSempro.anggotaPenguji1)
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_ANGGOTAPENGUJI2_ID, detSempro.anggotaPenguji2)
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_RUANGAN, detSempro.namaRuangan)
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_TANGGAL, detSempro.tanggalSempro)
                mBundle.putString(DetailJadwalSeminarFragment.EXTRA_JAM, detSempro.jamSempro)
                it.findNavController().navigate(R.id.action_jadwalFragment_to_detailJadwalFragment, mBundle)
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