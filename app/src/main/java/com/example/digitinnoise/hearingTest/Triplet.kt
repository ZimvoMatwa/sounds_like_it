package com.example.digitinnoise.hearingTest

data class Triplet(val digit1: Int, val digit2: Int, val digit3: Int){
     fun answer(): String {
        return "$digit1$digit2$digit3"
    }
}