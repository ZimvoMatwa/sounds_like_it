package com.example.digitinnoise.hearingTest

class HearingTest(tripletGenerator: TripletGenerator) : Test {
    private var currentRoundIndex = 0
    private var difficulty = 5
    private var triplets: MutableList<Triplet>

    init {
        triplets = tripletGenerator.generate(10).toMutableList()
    }

    override fun nextRound(): Round {
        val triplet = triplets.elementAt(currentRoundIndex)
        currentRoundIndex++
        return HearingTestRound(noise = HearingTestNoise(), triplet = triplet)
    }

    override fun rounds(): Int {
        return triplets.size
    }

    override fun difficulty(): Int {
        return difficulty
    }

    override fun answer(answer: String) {
        if (answer == triplets.elementAt(currentRoundIndex - 1).answer()) {
            difficulty++
        } else {
            difficulty--
        }
    }
}