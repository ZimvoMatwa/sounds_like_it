package com.example.digitinnoise.hearingTest.domain

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class AndroidAudioPlayer @Inject constructor(
    @ActivityContext private val context: Context
) : AudioPlayer {
    private var player: MediaPlayer? = null

    override fun create(): AudioPlayer {
        return AndroidAudioPlayer(context)
    }

    override fun play(resource: Int) {
        player = MediaPlayer.create(context,resource)
        player?.start()
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}