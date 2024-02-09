package com.setyo.similartytextapp.ui.penilaian

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.data.remote.response.DetpenilaianDataItem
import com.setyo.similartytextapp.databinding.ItemRowPengujiBinding

class DetailPenilaianListAdapter(private val detPenilaian: List<DetpenilaianDataItem>):RecyclerView.Adapter<DetailPenilaianListAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowPengujiBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(detPenilaian: DetpenilaianDataItem) {
            binding.apply {
                textViewRolePenguji.text = detPenilaian.levelDosen
                textViewNamaPenguji.text = detPenilaian.namaDosen
                textViewKetJudul.text = detPenilaian.judul
                textViewKetLatar.text = detPenilaian.latarBelakang
                textViewKetRumusan.text = detPenilaian.rumusanMasalah
                textViewKetbatasan.text = detPenilaian.batasanMasalah
                textViewKetTujuan.text = detPenilaian.tujuan
                textViewKetManfaat.text = detPenilaian.manfaat
                textViewKetTinjauan.text = detPenilaian.tinjauanPustaka
                textViewKetMetodologi.text = detPenilaian.metodologi
                textViewKetKerangka.text = detPenilaian.kerangkaPemikiran
                textViewKetJadwal.text = detPenilaian.jadwalKegiatan
                textViewKetRiwayat.text = detPenilaian.riwayatPenilitian
                textViewKetDaftar.text = detPenilaian.daftarPustaka
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowPengujiBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(detPenilaian[position])
    }

    override fun getItemCount(): Int = detPenilaian.size

}