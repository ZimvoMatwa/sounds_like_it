package com.example.digitinnoise

import com.example.digitinnoise.hearingTest.HearingTest
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HearingTestTest {

    @Test
    fun `a test consists of 10 rounds`() {
        val hearingTest = HearingTest()

        assertThat(hearingTest.rounds()).isEqualTo(10)
    }

    @Test
    fun `the test difficulty starts at 5`() {
        val hearingTest = HearingTest()

        assertThat(hearingTest.difficulty()).isEqualTo(5)
    }

    @Test
    fun `the test difficulty increases when the user answers correctly`() {
        val hearingTest = HearingTest()

        hearingTest.nextRound()
        hearingTest.answer("111")
        assertThat(hearingTest.difficulty()).isEqualTo(6)
    }

    @Test
    fun `the test difficulty decreases when the user answers incorrectly`(){
        val hearingTest = HearingTest()

        hearingTest.nextRound()
        hearingTest.answer("000")
        assertThat(hearingTest.difficulty()).isEqualTo(4)
    }
}