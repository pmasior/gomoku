package edu.pm.gomoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PlayerTest {
    private Player player2;

    @BeforeEach
    public void setUp() {
        player2 = new Player(2);
    }

    @Test
    public void givenCreatedPlayer_whenGetPlayerNumber_thenReturnPlayerNumber() {

        int result = player2.getPlayerNumber();

        Assertions.assertEquals(2, result);
    }

    @Test
    public void givenIncrementNumberOfWins_whenGetNumberOfWins_thenReturnNumberOfWins() {
        player2.incrementNumberOfWins();

        int result = player2.getNumberOfWins();

        Assertions.assertEquals(1, result);
    }
}
