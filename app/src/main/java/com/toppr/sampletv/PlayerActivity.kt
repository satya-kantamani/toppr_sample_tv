package com.toppr.sampletv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.Listener
import com.google.android.exoplayer2.ui.StyledPlayerView
import java.lang.ref.WeakReference

class PlayerActivity : AppCompatActivity() {

    lateinit var player : ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        player = ExoPlayer.Builder(this).build()
        findViewById<StyledPlayerView>(R.id.player_view).player = player

        val mediaItem: MediaItem = MediaItem.fromUri("https://www.youtube.com/watch?v=eKZz5djxtEo")
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    companion object {
        fun startActivity(weakReference: WeakReference<Context>) {
            val intent = Intent(weakReference.get(), PlayerActivity::class.java)
            weakReference.get()?.startActivity(intent)
        }
    }
}