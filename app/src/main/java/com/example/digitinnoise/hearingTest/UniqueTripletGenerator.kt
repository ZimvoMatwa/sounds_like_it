package com.example.digitinnoise.hearingTest

import kotlin.random.Random

class UniqueTripletGenerator(private val randomNumberGenerator: Random) {
    private val triplets = mutableListOf<Triplet>()

    fun generate(size: Int): List<Triplet> {
        while (triplets.size < size) {
            val nextTriplet = Triplet(
                randomNumberGenerator.nextInt(1, 9),
                randomNumberGenerator.nextInt(1, 9),
                randomNumberGenerator.nextInt(1, 9)
            )
            if (triplets.contains(nextTriplet)) {
                continue
            }

            if (triplets.isNotEmpty()) {
                val previousTriplet = triplets.last()

                if (previousTriplet.digit1 == nextTriplet.digit1 ||
                    previousTriplet.digit2 == nextTriplet.digit2 ||
                    previousTriplet.digit3 == nextTriplet.digit3
                ) {
                    continue
                }
            }

            triplets.add(
                nextTriplet
            )
        }

        return triplets
    }
}