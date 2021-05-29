package com.dmd.tenniskata

class TennisGame(var playerOne: Player, var playerTwo: Player) {
    private val deuceValue: String = "Deuce"
    fun currentSituation(): String{
        isWinner()?.let {
            return it
        }
        if (playerOne.isDeuceWithOpponentScore(playerTwo.score)){
            return deuceValue
        }
        if (playerOne.score == playerTwo.score && (playerOne.score < 3)){
            return "${scoreToString(playerOne.score)} all"
        }
        if (playerOne.score > (playerTwo.score + 2) && playerOne.score >= 2){
            return "${playerOne.playerName} has advantage"
        } else if (playerTwo.score > (playerOne.score + 2) && playerTwo.score >= 2){
            return "${playerTwo.playerName} has advantage"
        }
        if ((playerOne.score >= 3 && playerTwo.score >= 3) && playerTwo.score == playerOne.score){
            return deuceValue
        }
        return "${scoreToString(playerOne.score)} - ${scoreToString(playerTwo.score)}"
    }

    private fun isWinner(): String?{
        return when {
            playerOne.sets > 0 -> {
                "${playerOne.playerName} wins"
            }
            playerTwo.sets > 0 -> {
                "${playerTwo.playerName} wins"
            }
            else -> {
                null
            }
        }
    }

    private fun scoreToString(score: Int) : String{
        return when(score){
            0 -> "Love"
            1 -> "Fifteen"
            2 -> "Thirty"
            3 -> "Forty"
            else -> "Not possible"
        }
    }


    fun simulateGame(playerOneScore: Int, playerTwoScore: Int){

        for (playerOneIndex in 1..playerOneScore){
            playerOne.scored()
            CompareUtil().compareWithOpponentScore(playerOne,playerTwo)
        }

        for (playerTwoIndex in 1..playerTwoScore){
            playerTwo.scored()
            CompareUtil().compareWithOpponentScore(playerOne,playerTwo)
        }
    }

    fun clearScoreSetsForTests(){
        playerOne.score = 0
        playerTwo.score = 0

        playerOne.isAdvantage = false
        playerTwo.isAdvantage = false

        playerOne.sets = 0
        playerTwo.sets = 0
    }
}