package com.dmd.tenniskata

data class Player(var playerName: String){
    var score: Int = 0
    var sets: Int = 0
    var isAdvantage: Boolean = false
    var isWinner: Boolean = false

    fun isDeuceWithOpponentScore(scoreToCompare: Int): Boolean{
        return scoreToCompare == score && score > 3

    }

    fun scored(){
        if (isAdvantage){
            sets += 1
            score = 0
        } else {
            score += 1
        }

    }
}
