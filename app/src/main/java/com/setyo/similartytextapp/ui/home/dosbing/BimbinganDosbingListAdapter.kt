package com.setyo.similartytextapp.ui.home.dosbing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.setyo.similartytextapp.data.remote.response.UpdateBimbingandosenDataItem
import com.setyo.similartytextapp.databinding.ItemDosenBimbinganBinding

class BimbinganDosbingListAdapter(
    private val listBimbingan: List<UpdateBimbingandosenDataItem?>
) : RecyclerView.Adapter<BimbinganDosbingListAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemDosenBimbinganBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(listBimbingan: UpdateBimbingandosenDataItem) {
            binding.apply {
                if (listBimbingan.balasanketBimbingan == null) {
                    cardViewBimbingan.visibility = View.GONE
                    textViewTanggalBalasan.visibility = View.GONE
                }
                textViewTanggal.text = listBimbingan.tanggalBimbingan
                textViewKet.text = listBimbingan.ketBimbingan
                textViewTanggalBalasan.text = listBimbingan.balasantanggalBimbingan
                textViewKetBalasan.text = listBimbingan.balasanketBimbingan
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemDosenBimbinganBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = listBimbingan[position]
        currentItem?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = listBimbingan.size
}