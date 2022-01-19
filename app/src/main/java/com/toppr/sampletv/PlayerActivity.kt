package com.toppr.sampletv

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import java.lang.ref.WeakReference


class PlayerActivity : AppCompatActivity() {

    private lateinit var player: ExoPlayer
    private lateinit var playerView: StyledPlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerView = findViewById(R.id.player_view)

        loadVideoPlayer()
    }

    private fun loadVideoPlayer(){
        playerView.visibility = View.VISIBLE
        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        val mediaItem: MediaItem =
            MediaItem.fromUri(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            if (!playerView.isControllerFullyVisible) {
                playerView.showController()
            }
        }
        return false
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