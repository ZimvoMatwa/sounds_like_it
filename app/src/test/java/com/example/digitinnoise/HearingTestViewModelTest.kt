package com.example.digitinnoise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.digitinnoise.hearingTest.HearingTestViewModel
import com.example.digitinnoise.hearingTest.HearingTestViewState
import com.example.digitinnoise.hearingTest.Triplet
import com.example.digitinnoise.utils.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HearingTestViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val hearingTest = mockk<com.example.digitinnoise.hearingTest.Test>()
    val triplet = Triplet(1,1,1)

    @Test
    fun `the first round starts 3 seconds after opening the test screen`() = runTest {
        every { hearingTest.nextRound() } returns triplet
        val viewModel = HearingTestViewModel(hearingTest)

        viewModel.uiState.test {
            viewModel.startTest()

            delay(2999)
            assertThat(expectMostRecentItem()).isEqualTo(HearingTestViewState.Waiting)
            delay(3000)
            assertThat(expectMostRecentItem()).isEqualTo(HearingTestViewState.Loaded(triplet))
        }
    }

    @Test
    fun `each following round must start 2 seconds after submission of the previous round`() =
        runTest {
            val triplet1 = triplet
            val triplet2 = triplet
            every { hearingTest.nextRound() } returns triplet1 andThen triplet2
            val viewModel = HearingTestViewModel(hearingTest)

            viewModel.startTest()
            viewModel.uiState.test {
                viewModel.submit()
                delay(1999)
                assertThat(expectMostRecentItem()).isEqualTo(HearingTestViewState.Waiting)
                delay(2000)
                assertThat(expectMostRecentItem()).isEqualTo(HearingTestViewState.Loaded(triplet2))
            }
        }
}