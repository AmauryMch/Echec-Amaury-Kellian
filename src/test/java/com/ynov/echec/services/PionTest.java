package com.ynov.echec.services;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PionTest {

    @Test
    void deplacerValidOneStepForward() {
        Pion pion = new Pion(0, 1);
        pion.deplacer(0, 2);
        assertEquals(2, pion.getY());
    }

    @Test
    void deplacerValidTwoStepsForwardFromInitialPosition() {
        Pion pion = new Pion(0, 1);
        pion.deplacer(0, 3);
        assertEquals(3, pion.getY());
    }

    @Test
    void deplacerInvalidTwoStepsForwardNotFromInitialPosition() {
        Pion pion = new Pion(0, 2);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacer(0, 4)
        );
        assertEquals("Invalid move for a pawn.", exception.getMessage());
    }

    @Test
    void deplacerInvalidSidewaysMove() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacer(1, 2)
        );
        assertEquals("Invalid move for a pawn.", exception.getMessage());
    }

    @Test
    void deplacerInvalidBackwardMove() {
        Pion pion = new Pion(0, 2);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacer(0, 1)
        );
        assertEquals("Invalid move for a pawn.", exception.getMessage());
    }

    @Test
    void deplacerInvalidMoveToSamePosition() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacer(0, 1)
        );
        assertEquals("Invalid move for a pawn.", exception.getMessage());
    }

    @Test
    void deplacerInvalidMoveOutOfBoard() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacer(0, 10)
        );
        assertEquals("Target position is out of the board.", exception.getMessage());
    }

    @Test
    public void checkPossibilitiesFromInitialPosition() {
        Pion pion = new Pion(0, 1);
        List<int[]> possibilities = pion.checkPossibilities(0, 1);
        assertEquals(2, possibilities.size());
        assertArrayEquals(new int[]{0, 2}, possibilities.get(0));
        assertArrayEquals(new int[]{0, 3}, possibilities.get(1));
    }

    @Test
    public void checkPossibilitiesFromNonInitialPosition() {
        Pion pion = new Pion(0, 2);
        List<int[]> possibilities = pion.checkPossibilities(0, 2);
        assertEquals(1, possibilities.size());
        assertArrayEquals(new int[]{0, 3}, possibilities.get(0));
    }

    @Test
    public void checkPossibilitiesAtBoardEdge() {
        Pion pion = new Pion(0, 7);
        List<int[]> possibilities = pion.checkPossibilities(0, 7);
        assertTrue(possibilities.isEmpty());
    }

    @Test
    public void checkPossibilitiesFromInvalidPosition() {
        Pion pion = new Pion(0, -1);
        List<int[]> possibilities = pion.checkPossibilities(0, -1);
        assertTrue(possibilities.isEmpty());
    }
}
