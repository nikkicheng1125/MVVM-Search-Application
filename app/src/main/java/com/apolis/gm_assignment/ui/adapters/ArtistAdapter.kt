package com.apolis.gm_assignment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.gm_assignment.data.Artist
import com.apolis.gm_assignment.databinding.HolderArtistBinding
import com.apolis.gm_assignment.ui.hodlers.ArtistHolder

class ArtistAdapter(var list: List<Artist>): RecyclerView.Adapter<ArtistHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderArtistBinding.inflate(layoutInflater, parent, false)
        return ArtistHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

     fun setNewList(myList: List<Artist>) {
         this.list = myList
         notifyDataSetChanged()
    }
}