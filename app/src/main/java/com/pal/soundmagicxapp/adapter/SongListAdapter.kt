package com.pal.soundmagicxapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.pal.soundmagicxapp.MyExoplayer
import com.pal.soundmagicxapp.activity.ActivityPlayer
import com.pal.soundmagicxapp.databinding.ActivityPlayerBinding
import com.pal.soundmagicxapp.databinding.SongListLayoutBinding
import com.pal.soundmagicxapp.model.CategoryModel
import com.pal.soundmagicxapp.model.SongModel

class SongListAdapter(private val categoryList: List<String>) :
    RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    private var onItemClick: ((SongModel) -> Unit)? = null
    fun setOnItemClickListener(listener: (SongModel) -> Unit) {
        onItemClick = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding =
            SongListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class SongViewHolder(private val binding: SongListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(songId: String) {
            FirebaseFirestore.getInstance().collection("songs").document(songId).get().addOnSuccessListener {
                val song = it.toObject(SongModel::class.java)
              song?.apply {
                binding.titleTextview.text = song.title
                  Glide.with(itemView)
                      .load(song.image)
                      .centerCrop()
                      .into(binding.imageViewMusic)
                  binding.root.setOnClickListener {
                      MyExoplayer.startMusic(binding.root.context,song)
                      onItemClick?.invoke(song)
                  }
              }
            }
        }
    }
}
