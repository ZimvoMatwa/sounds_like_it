package com.example.digitinnoise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.digitinnoise.hearingTest.AudioPlayer
import com.example.digitinnoise.hearingTest.HearingTestViewModel
import com.example.digitinnoise.hearingTest.HearingTestViewState
import com.example.digitinnoise.hearingTest.Round
import com.example.digitinnoise.utils.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HearingTestViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val hearingTest = mockk<com.example.digitinnoise.hearingTest.Test>()
    val audioPlayer = mockk<AudioPlayer>()

    @Before
    fun setup() {
        every { audioPlayer.play(any()) } returns Unit
    }

    @Test
    fun `the first round starts 3 seconds after opening the test screen`() = runTest {
        val round = mockk<Round>()
        coEvery { round.play(audioPlayer) } returns Unit
        every { hearingTest.nextRound() } returns round
        val viewModel = HearingTestViewModel(hearingTest, audioPlayer)

        viewModel.uiState.test {
            viewModel.startTest()

            delay(2999)
            assertThat(expectMostRecentItem()).isEqualTo(HearingTestViewState.Waiting)
            delay(3000)
            assertThat(expectMostRecentItem()).isEqualTo(HearingTestViewState.Loaded(round))
        }
    }

    @Test
    fun `each following round must start 2 seconds after submission of the previous round`() =
        runTest {
            val round1 = mockk<Round>()
            val round2 = mockk<Round>()
            coEvery { round1.play(audioPlayer) } returns Unit
            coEvery { round2.play(audioPlayer) } returns Unit
            every { hearingTest.nextRound() } returns round1 andThen round2
            val viewModel = HearingTestViewModel(hearingTest, audioPlayer)

            viewModel.startTest()
            viewModel.uiState.test {
                viewModel.submit()
                delay(1999)
                assertThat(expectMostRecentItem()).isEqualTo(HearingTestViewState.Waiting)
                delay(2000)
                assertThat(expectMostRecentItem()).isEqualTo(HearingTestViewState.Loaded(round2))
            }
        }

    @Test
    fun `each round plays a triplet`() = runTest {
        val round1 = mockk<Round>()
        val round2 = mockk<Round>()
        coEvery { round1.play(audioPlayer) } returns Unit
        coEvery { round2.play(audioPlayer) } returns Unit
        every { hearingTest.nextRound() } returns round1 andThen round2
        val viewModel = HearingTestViewModel(hearingTest, audioPlayer)

        viewModel.uiState.test {
            viewModel.startTest()
            viewModel.submit()

            delay(4000)

            coVerify(exactly = 1) { round1.play(audioPlayer) }
            coVerify(exactly = 1) { round2.play(audioPlayer) }

            cancelAndIgnoreRemainingEvents()
        }
    }
}