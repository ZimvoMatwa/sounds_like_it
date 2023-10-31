package com.example.digitinnoise.hearingTest

class HearingTest(tripletGenerator: TripletGenerator) : Test {
    private var currentRoundIndex = 0
    private var difficulty = 5
    private var triplets: MutableList<Triplet>
    private var rounds: MutableList<Round> = mutableListOf()

    init {
        triplets = tripletGenerator.generate(10).toMutableList()
    }

    override fun nextRound(): Round {
        val triplet = triplets.elementAt(currentRoundIndex)
        currentRoundIndex++
        val round = HearingTestRound(difficulty = difficulty, triplet = triplet)
        rounds.add(round)
        return round
    }

    override fun rounds(): Int {
        return triplets.size
    }

    override fun difficulty(): Int {
        return difficulty
    }

    override fun answer(answer: String) {
        val currentRound = rounds.elementAt(currentRoundIndex - 1)
        currentRound.answer(answer)
        if (currentRound.answerIsCorrect()) {
            difficulty++
        } else {
            difficulty--
        }
    }

    override fun score(): Int {
        return rounds.fold(0) { acc, round -> acc + round.score() }
    }
}