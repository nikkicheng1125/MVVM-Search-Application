package com.apolis.gm_assignment.ui.hodlers

import androidx.recyclerview.widget.RecyclerView
import com.apolis.gm_assignment.data.Artist
import com.apolis.gm_assignment.databinding.HolderArtistBinding
import java.text.SimpleDateFormat

class ArtistHolder(val binding: HolderArtistBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(artist: Artist) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.parse(artist.releaseDate)

        val targetSdf = SimpleDateFormat.getDateInstance()

        val releaseDate = targetSdf.format(date)

        binding.tvName.text = artist.artistName
        binding.tvTrackName.text = artist.trackName
        binding.tvTrackPrice.text = "Price is : ${artist.trackPrice}"
        binding.tvGenre.text = "Genre: ${artist.genre}"
        binding.tvReleaseDate.text="Release Date: $releaseDate"
    }
}