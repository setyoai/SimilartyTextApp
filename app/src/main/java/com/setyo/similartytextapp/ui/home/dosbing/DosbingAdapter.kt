package com.setyo.similartytextapp.ui.home.dosbing

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.data.remote.response.BimbinganDosenDataItem
import com.setyo.similartytextapp.databinding.ItemRowBimbinganBinding

class DosbingAdapter(private val dosbing: List<BimbinganDosenDataItem>):RecyclerView.Adapter<DosbingAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowBimbinganBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(dosbing: BimbinganDosenDataItem) {
            binding.apply {
                textViewName.text = dosbing.namaBimbingan
                textViewKet.text = dosbing.ketBimbingan
                textViewDate.text = dosbing.tanggalBimbingan
            }
            binding.mahasiswaBimbingan.setOnClickListener {
                val intent = Intent(itemView.context, BimbinganDosenActivity::class.java)
                intent.putExtra(BimbinganDosenActivity.EXTRA_BIMBINGAN_ID, dosbing.idbimbingan)
                intent.putExtra(BimbinganDosenActivity.EXTRA_NAME_MHS, dosbing.namaBimbingan)
                itemView.context.startActivity(intent)
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