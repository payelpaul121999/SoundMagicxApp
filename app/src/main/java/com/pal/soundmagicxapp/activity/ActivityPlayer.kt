package com.pal.soundmagicxapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.pal.soundmagicxapp.MyExoplayer
import com.pal.soundmagicxapp.R
import com.pal.soundmagicxapp.databinding.ActivityPlayerBinding
import com.pal.soundmagicxapp.model.CategoryModel
import com.pal.soundmagicxapp.model.SongModel

class ActivityPlayer : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    lateinit var exoPlayer: ExoPlayer
    var playerListener = object: Player.Listener{
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            showGif(isPlaying)
            super.onIsPlayingChanged(isPlaying)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedIntent = intent
        val receivedCategory: SongModel? = receivedIntent.getParcelableExtra("song")

        MyExoplayer.getCurrentSong()?.apply {
            binding.textSongName.text= title
            Glide.with(this@ActivityPlayer)
                .load(receivedCategory!!.image)
                .into(binding.albumImage)
            Glide.with(binding.albumImageGif).load(R.drawable.media_playing)
                .into(binding.albumImageGif)
            exoPlayer = MyExoplayer.getInstance()!!
            exoPlayer.addListener(playerListener)
            binding.playerView.player = exoPlayer
            binding.playerView.showController()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.removeListener(playerListener)
    }
    private fun showGif(playing: Boolean) = if (playing){
        binding.albumImageGif.visibility = View.INVISIBLE
    }else{
        binding.albumImageGif.visibility = View.VISIBLE
    }
}