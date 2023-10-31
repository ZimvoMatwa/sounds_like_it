package com.example.digitinnoise

import com.example.digitinnoise.hearingTest.HearingTest
import com.example.digitinnoise.hearingTest.Triplet
import com.example.digitinnoise.hearingTest.TripletGenerator
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class HearingTestTest {
    val tripletGenerator = mockk<TripletGenerator>()
    lateinit var hearingTest: HearingTest

    @Before
    fun setup() {
        every { tripletGenerator.generate(10) } returns listOf<Triplet>(
            Triplet(1, 1, 1),
            Triplet(2, 2, 2),
            Triplet(3, 3, 3),
            Triplet(4, 4, 4),
            Triplet(5, 5, 5),
            Triplet(6, 6, 6),
            Triplet(7, 7, 7),
            Triplet(8, 8, 8),
            Triplet(9, 9, 9),
            Triplet(1, 2, 3),
        )
        hearingTest = HearingTest(tripletGenerator)
    }

    @Test
    fun `a test consists of 10 rounds`() {
        assertThat(hearingTest.rounds()).isEqualTo(10)
    }

    @Test
    fun `the test difficulty starts at 5`() {
        assertThat(hearingTest.difficulty()).isEqualTo(5)
    }

    @Test
    fun `the test difficulty increases when the user answers correctly`() {
        hearingTest.nextRound()
        hearingTest.answer("111")
        assertThat(hearingTest.difficulty()).isEqualTo(6)

        hearingTest.nextRound()
        hearingTest.answer("222")
        assertThat(hearingTest.difficulty()).isEqualTo(7)
    }

    @Test
    fun `the test difficulty decreases when the user answers incorrectly`() {
        hearingTest.nextRound()
        hearingTest.answer("000")
        assertThat(hearingTest.difficulty()).isEqualTo(4)

        hearingTest.nextRound()
        hearingTest.answer("999")
        assertThat(hearingTest.difficulty()).isEqualTo(3)
    }
}



