package com.sparkling.smile

import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class VideoActivity : AppCompatActivity() {
    private lateinit var constraintLayoutRoot : ConstraintLayout
    private lateinit var exoPlayerview : PlayerView
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaSource: MediaSource
    private lateinit var urlType : URLType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        findView()
        initPlayer()
    }

    private fun findView(){
        constraintLayoutRoot = findViewById(R.id.constraintLayoutRoot)
        exoPlayerview = findViewById(R.id.video_view)
    }

    private fun initPlayer(){
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        simpleExoPlayer.addListener(playerListener)

        createMediaSource()
        exoPlayerview.player = simpleExoPlayer
        simpleExoPlayer.setMediaSource(mediaSource)
        simpleExoPlayer.prepare()

    }

    private fun createMediaSource() {
        urlType = URLType.MP4
        urlType.url = "https://test.arnavpuranik.workers.dev/0:/Copy%20of%20M1V1%20ABCD.mp4"

        simpleExoPlayer.seekTo(0)

        val dataSourceFactory : DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, applicationInfo.name)
        )

        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
            MediaItem.fromUri(Uri.parse(urlType.url))
        )
    }

    override fun onResume() {
        super.onResume()
        simpleExoPlayer.playWhenReady = true
        simpleExoPlayer.play()
    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer.pause()
        simpleExoPlayer.playWhenReady = false
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.pause()
        simpleExoPlayer.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()

        simpleExoPlayer.removeListener(playerListener)
        simpleExoPlayer.stop()
        simpleExoPlayer.clearMediaItems()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private var playerListener = object : Player.Listener {

        override fun onRenderedFirstFrame() {
            super.onRenderedFirstFrame()
            exoPlayerview.useController = true
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            Toast.makeText(this@VideoActivity, "${error.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

enum class URLType(var url:String){
    MP4("")
}