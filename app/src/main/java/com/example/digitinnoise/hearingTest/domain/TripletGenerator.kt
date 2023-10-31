package com.example.digitinnoise.hearingTest.domain

interface TripletGenerator {
    fun generate(size: Int): List<Triplet>
}