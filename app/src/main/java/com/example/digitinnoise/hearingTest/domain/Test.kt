package com.example.digitinnoise.hearingTest.domain

interface Test {
    fun nextRound(): Round
    fun rounds(): Int
    fun difficulty(): Int
    fun answer(answer: String)
    fun score(): Int
}