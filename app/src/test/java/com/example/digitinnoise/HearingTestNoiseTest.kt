package com.example.digitinnoise

import com.example.digitinnoise.hearingTest.AudioPlayer
import com.example.digitinnoise.hearingTest.HearingTestNoise
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class HearingTestNoiseTest {
    private val audioPlayer = mockk<AudioPlayer>()

    @Test
    fun `noise intensifies as the difficulty increases`() {
        every { audioPlayer.create() } returns audioPlayer
        every { audioPlayer.play(any()) } returns Unit

        val noise = HearingTestNoise(difficulty = 5)

        noise.play(audioPlayer)
        verify { audioPlayer.play(R.raw.noise_5) }

        val intenseNoise = HearingTestNoise(difficulty = 6)
        intenseNoise.play(audioPlayer)
        verify { audioPlayer.play(R.raw.noise_6) }
    }

    @Test
    fun `the noise difficulty does not go above 10`() {
        every { audioPlayer.create() } returns audioPlayer
        every { audioPlayer.play(any()) } returns Unit

        val noise = HearingTestNoise(difficulty = 11)
        noise.play(audioPlayer)
        verify { audioPlayer.play(R.raw.noise_10) }
    }

    @Test
    fun `the noise difficulty does not go below 1`() {
        every { audioPlayer.create() } returns audioPlayer
        every { audioPlayer.play(any()) } returns Unit

        val noise = HearingTestNoise(difficulty = 0)
        noise.play(audioPlayer)
        verify { audioPlayer.play(R.raw.noise_1) }
    }
}