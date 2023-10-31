package com.example.digitinnoise.hearingTest

interface TripletGenerator {
    fun generate(size: Int): List<Triplet>
}