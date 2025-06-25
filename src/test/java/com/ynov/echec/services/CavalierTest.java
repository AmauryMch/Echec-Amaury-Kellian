package com.ynov.echec.services;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CavalierTest {

    @Test
    void validMoveAndUpdatesPosition() {
        Cavalier cavalier = new Cavalier(0, 0);
        cavalier.deplacer(2, 1);
        assertEquals(2, cavalier.getX());
        assertEquals(1, cavalier.getY());
    }

    @Test
    void otherValidMoveAndUpdatesPosition() {
        Cavalier cavalier = new Cavalier(0, 0);
        cavalier.deplacer(1, 2);
        assertEquals(1, cavalier.getX());
        assertEquals(2, cavalier.getY());
    }

    @Test
    void invalidMoveDoesNotUpdatePosition() {
        Cavalier cavalier = new Cavalier(0, 0);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cavalier.deplacer(4, 4));

        assertEquals("Invalid move for a knight.", exception.getMessage());
        assertEquals(0, cavalier.getX());
        assertEquals(0, cavalier.getY());
    }

    @Test
    void otherInvalidMoveDoesNotUpdatePosition() {
        Cavalier cavalier = new Cavalier(0, 0);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cavalier.deplacer(2, 2));

        assertEquals("Invalid move for a knight.", exception.getMessage());
        assertEquals(0, cavalier.getX());
        assertEquals(0, cavalier.getY());
    }

    @Test
    void secondOtherInvalidMoveDoesNotUpdatePosition() {
        Cavalier cavalier = new Cavalier(0, 0);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cavalier.deplacer(1, 3));

        assertEquals("Invalid move for a knight.", exception.getMessage());
        assertEquals(0, cavalier.getX());
        assertEquals(0, cavalier.getY());
    }

    @Test
    void samePositionThrowsException() {
        Cavalier cavalier = new Cavalier(0, 0);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cavalier.deplacer(0, 0));
        assertEquals("Invalid move for a knight.", exception.getMessage());
        assertEquals(0, cavalier.getX());
        assertEquals(0, cavalier.getY());
    }

    @Test
    void checkPossibilitiesReturnsValidMovesWithinBounds() {
        Cavalier cavalier = new Cavalier(4, 4);
        List<int[]> possibilities = cavalier.checkPossibilities(4, 4);

        assertTrue(possibilities.stream().anyMatch(arr -> arr[0] == 6 && arr[1] == 5));
        assertTrue(possibilities.stream().anyMatch(arr -> arr[0] == 5 && arr[1] == 6));
        assertTrue(possibilities.stream().anyMatch(arr -> arr[0] == 2 && arr[1] == 3));
        assertTrue(possibilities.stream().anyMatch(arr -> arr[0] == 3 && arr[1] == 2));
    }

    @Test
    void checkPossibilitiesExcludesOutOfBoundsMoves() {
        Cavalier cavalier = new Cavalier(0, 0);
        List<int[]> possibilities = cavalier.checkPossibilities(0, 0);
        assertFalse(possibilities.contains(new int[]{-2, -1}));
        assertFalse(possibilities.contains(new int[]{-1, -2}));
    }
}