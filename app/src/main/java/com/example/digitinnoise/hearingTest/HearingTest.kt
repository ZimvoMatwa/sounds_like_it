package com.example.digitinnoise.hearingTest

class HearingTest : Test {
    private var rounds = mutableListOf<Round>()
    private var currentRoundIndex = 0
    private var difficulty = 5

    init {
        for (i in 1..10){
            rounds.add(
                Round(Triplet(1,1,1))
            )
        }
    }

    override fun nextRound(): Round {
        return rounds.elementAt(currentRoundIndex)
    }

    override fun rounds(): Int {
        return rounds.size
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