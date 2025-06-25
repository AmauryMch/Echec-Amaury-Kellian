package com.ynov.echec.services;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TourTest {

    @Test
    public void deplacerToValidHorizontalPosition() {
        Tour tour = new Tour(4, 4);
        tour.deplacer(4, 7);
        assertEquals(4, tour.getX());
        assertEquals(7, tour.getY());
    }

    @Test
    public void deplacerToValidVerticalPosition() {
        Tour tour = new Tour(4, 4);
        tour.deplacer(7, 4);
        assertEquals(7, tour.getX());
        assertEquals(4, tour.getY());
    }

    @Test
    public void deplacerToInvalidDiagonalPosition() {
        Tour tour = new Tour(4, 4);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tour.deplacer(5, 5));
        assertEquals("Invalid move for a rook.", exception.getMessage());
    }

    @Test
    public void deplacerToOutOfBoardPosition() {
        Tour tour = new Tour(4, 4);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tour.deplacer(8, 4));
        assertEquals("Target position is out of the board.", exception.getMessage());
    }

    @Test
    public void deplacerToSamePosition() {
        Tour tour = new Tour(4, 4);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> tour.deplacer(4, 4));
        assertEquals("Cannot move to the same position.", exception.getMessage());
    }

    @Test
    public void checkPossibilitiesFromCenterPosition() {
        Tour tour = new Tour(4, 4);
        List<int[]> possibilities = tour.checkPossibilities(4, 4);
        assertEquals(14, possibilities.size());
        assertTrue(containsArray(possibilities, new int[]{0, 4}));
        assertTrue(containsArray(possibilities, new int[]{4, 0}));
        assertTrue(containsArray(possibilities, new int[]{7, 4}));
        assertTrue(containsArray(possibilities, new int[]{4, 7}));
    }

    @Test
    public void checkPossibilitiesFromCornerPosition() {
        Tour tour = new Tour(0, 0);
        List<int[]> possibilities = tour.checkPossibilities(0, 0);
        assertEquals(14, possibilities.size());
        assertTrue(containsArray(possibilities, new int[]{7, 0}));
        assertTrue(containsArray(possibilities, new int[]{0, 7}));
    }

    @Test
    public void checkPossibilitiesFromEdgePosition() {
        Tour tour = new Tour(0, 4);
        List<int[]> possibilities = tour.checkPossibilities(0, 4);
        assertEquals(14, possibilities.size());
        assertTrue(containsArray(possibilities, new int[]{7, 4}));
        assertTrue(containsArray(possibilities, new int[]{0, 0}));
    }

    @Test
    public void checkPossibilitiesFromInvalidPosition() {
        Tour tour = new Tour(-1, 4);
        List<int[]> possibilities = tour.checkPossibilities(-1, 4);
        assertTrue(possibilities.isEmpty());
    }

    private boolean containsArray(List<int[]> list, int[] target) {
        for (int[] array : list) {
            if (array[0] == target[0] && array[1] == target[1]) {
                return true;
            }
        }
        return false;
    }
}
