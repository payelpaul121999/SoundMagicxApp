package com.pal.soundmagicxapp.activity


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pal.soundmagicxapp.adapter.SongListAdapter
import com.pal.soundmagicxapp.databinding.ActivitySongListBinding
import com.pal.soundmagicxapp.model.CategoryModel



class ActivitySongList :AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    lateinit var adapter: SongListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val receivedIntent = intent
        val receivedCategory: CategoryModel? = receivedIntent.getParcelableExtra("category")

        setAdapter(receivedCategory?.songs ?: emptyList(), receivedCategory?.imgUrl ?: "")

    }

  private  fun setAdapter(categoryList : List<String>,imageUrl:String){
      Glide.with(this)
          .load(imageUrl)
          .centerCrop()
          .into(binding.categoryImageView)
       binding.recyclerviewSongList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
         adapter = SongListAdapter(categoryList)
      binding.recyclerviewSongList.adapter = adapter
      adapter.setOnItemClickListener {
          val intent = Intent(this,ActivityPlayer::class.java)
          intent.putExtra("song", it)
          startActivity(intent)
      }
    }
}