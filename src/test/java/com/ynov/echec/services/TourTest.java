package com.ynov.echec.services;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TourTest {

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
