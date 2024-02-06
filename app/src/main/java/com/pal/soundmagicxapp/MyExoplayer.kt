package com.pal.soundmagicxapp

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.pal.soundmagicxapp.model.SongModel

object MyExoplayer {
    private var exoPlayer: ExoPlayer? = null
    private var currentSong: SongModel? = null
    fun getInstance(): ExoPlayer? {
        return exoPlayer
    }
    fun getCurrentSong() : SongModel?{
        return currentSong
    }
    fun startMusic(context: Context, song: SongModel) {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build()
        }
        if (currentSong != song) {
            currentSong = song
            currentSong?.url?.apply {
                val mediaItem = androidx.media3.common.MediaItem.fromUri(this)
                exoPlayer?.setMediaItem(mediaItem)
                exoPlayer?.prepare()
                exoPlayer?.play()
            }
        }
    }
}
