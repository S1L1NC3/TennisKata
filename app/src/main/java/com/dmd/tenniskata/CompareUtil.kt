package com.dmd.tenniskata

class CompareUtil {
    fun compareWithOpponentScore(playerOne: Player, playerTwo: Player ): Boolean{
        if (playerOne.score >= 3){
            playerOne.isAdvantage = true
            playerTwo.isAdvantage = false
            return true
        } else if (playerTwo.score >= 3){
            playerTwo.isAdvantage = true
            playerOne.isAdvantage = false
            return true
        }
        return  false
    }
}