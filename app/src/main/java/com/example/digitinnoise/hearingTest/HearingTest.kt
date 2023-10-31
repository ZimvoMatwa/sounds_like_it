package com.example.digitinnoise.hearingTest

class HearingTest : Test {
    private val rounds = listOf<Round>(
        Round()
    )
    private var currentRoundIndex = 0
    private var difficulty = 5

    override fun nextRound(): Round {
        return rounds.elementAt(currentRoundIndex)
    }

    override fun rounds(): Int {
        return 10
    }

    override fun difficulty(): Int {
        return difficulty
    }

    override fun answer(answer: String) {
        if (answer == rounds.elementAt(currentRoundIndex).answer()) {
            difficulty++
        } else {
            difficulty--
        }
    }
}