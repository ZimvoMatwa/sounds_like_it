package com.example.digitinnoise.hearingTest

interface Test {
    fun nextRound(): Round
    fun rounds():Int
    fun difficulty():Int
    fun answer(answer: String)
}