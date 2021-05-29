package com.dmd.tenniskata

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class TennisGameTest {
    var scoresToTest = arrayOf("Love","Fifteen","Thirty","Forty","Deuce")
    var players = arrayOf(Player("Marcus Willis"), Player("Roger Federer"))
    var gameInstance = TennisGame(players[0], players[1])
    lateinit var result : String

    @Before
    fun setUp() {
        gameInstance.clearScoreSetsForTests() //Clear everything before test's
    }

    /* Test Cases
    0 15 30 40 - advantage - SET_POINT
    if 40 40 deuce
    set is one game
    * game starts new | DONE
    * Player scores first ball (love - fifteen) | DONE
    * Second Player first ball (fifteen - love) | DONE
    * Both players score  (both fifteen) | DONE
    * Any player leads (thirty - love )  without opponent score | DONE
    * Other player leads ( love - thirty )  without opponent score | DONE
    * Players are Deuce | DONE
    * A player wins game | DONE
    * A player wins advantage | DONE
    * Another player advantage | DONE
    * A player wins AFTER advantage | MIGRATED ON WIN SITUATION
    * Another player wins AFTER advantage | MIGRATED ON WIN SITUATION
    */

    //Game started with "Love all" value
    @Test
    fun testGameStarted(){
        result = gameInstance.currentSituation()
        assertEquals("${scoresToTest[0]} all", result) //
    }

    //PlayerOne scored potential outcome true
    @Test
    fun testPlayerOneScored(){
        players[0].scored()

        result = gameInstance.currentSituation()
        assertEquals("${scoresToTest[1]} - ${scoresToTest[0]}", result) //Fifteen - Love
    }

    //PlayerTwo scored potential outcome true
    @Test
    fun testPlayerTwoScored(){
        players[1].scored()

        result = gameInstance.currentSituation()
        assertEquals("${scoresToTest[0]} - ${scoresToTest[1]}", result) //Fifteen - Love
    }

    //Players are equal after SCORING
    @Test
    fun testPlayerEqualFromPlayerOne(){
        players[0].scored()
        players[1].scored()

        result = gameInstance.currentSituation()
        assertEquals("${scoresToTest[1]} all", result) //Fifteen all
    }

    //Same Test case from Player 2 side
    @Test
    fun testPlayerEqualFromPlayerTwo(){
        gameInstance.simulateGame(2,2)

        result = gameInstance.currentSituation()
        assertEquals("${scoresToTest[2]} all", result) //Fifteen all
    }

    //Three in a row situation
    @Test
    fun testPlayerOneInRowThree(){
        gameInstance.simulateGame(2,0)

        result = gameInstance.currentSituation()
        assertEquals("${scoresToTest[2]} - ${scoresToTest[0]}", result) //Thirty - Love
    }

    @Test
    fun testPlayerTwoInRowThree(){
        gameInstance.simulateGame(0,2)

        result = gameInstance.currentSituation()
        assertEquals("${scoresToTest[0]} - ${scoresToTest[2]}", result) //Thirty - Love
    }

    //Players are deuce
    @Test
    fun testPlayerDeuce(){
        gameInstance.simulateGame(3,3)

        result = gameInstance.currentSituation()
        assertEquals(scoresToTest[4], result) //Deuce
    }

    //Winner shows
    @Test
    fun testPlayerOneWins(){
        gameInstance.simulateGame(4,0)

        result = gameInstance.currentSituation()
        assertEquals("${players[0].playerName} wins", result) //Marcus Willis wins
    }

    @Test
    fun testPlayerTwoWins(){
        gameInstance.simulateGame(0,4)

        result = gameInstance.currentSituation()
        assertEquals("${players[1].playerName} wins", result) //Roger Federer wins
    }

    @Test
    fun testPlayerTwoWinsAdvantage(){
        gameInstance.simulateGame(3,0)

        result = gameInstance.currentSituation()
        assertEquals("${players[0].playerName} has advantage", result) //Roger Federer wins
    }

    @Test
    fun testPlayerOneWinsAdvantage(){
        gameInstance.simulateGame(0,3)

        result = gameInstance.currentSituation()
        assertEquals("${players[1].playerName} has advantage", result) //Roger Federer wins
    }

    @After
    fun tearDown() {
    }

}