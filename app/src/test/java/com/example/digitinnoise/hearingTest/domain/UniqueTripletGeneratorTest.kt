package com.example.digitinnoise.hearingTest.domain

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.random.Random

class UniqueTripletGeneratorTest {
    @Test
    fun `each triplet is unique throughout the set`() {
        val randomNumberGenerator = mockk<Random>()
        every { randomNumberGenerator.nextInt(1, 9) } returnsMany listOf(
            1, 1, 1,
            1, 1, 1,
            2, 2, 2,
            3, 3, 3,
            4, 4, 4
        )

        val tripletGenerator = UniqueTripletGenerator(randomNumberGenerator)

        val triplets = tripletGenerator.generate(4)

        assertThat(triplets.elementAt(0)).isEqualTo(Triplet(1, 1, 1))
        assertThat(triplets.elementAt(1)).isEqualTo(Triplet(2, 2, 2))
        assertThat(triplets.elementAt(2)).isEqualTo(Triplet(3, 3, 3))
        assertThat(triplets.elementAt(3)).isEqualTo(Triplet(4, 4, 4))
    }

    @Test
    fun `each triplet must not have a repeated digit in the same position when compared to the previous triplet`() {
        val randomNumberGenerator = mockk<Random>()
        every { randomNumberGenerator.nextInt(1, 9) } returnsMany listOf(
            1, 1, 1,
            1, 2, 3, // should be skipped
            2, 3, 4,
            3, 3, 5, // should be skipped
            4, 4, 5,
            4, 3, 4,  // should be skipped
            5, 5, 6
        )

        val tripletGenerator = UniqueTripletGenerator(randomNumberGenerator)

        val triplets = tripletGenerator.generate(4)

        assertThat(triplets.elementAt(0)).isEqualTo(Triplet(1, 1, 1))
        assertThat(triplets.elementAt(1)).isEqualTo(Triplet(2, 3, 4))
        assertThat(triplets.elementAt(2)).isEqualTo(Triplet(4, 4, 5))
        assertThat(triplets.elementAt(3)).isEqualTo(Triplet(5, 5, 6))
    }
}