package com.ynov.echec.services;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FouTest {

    @Test
    public void checkPossibilitiesFromCenterPosition() {
        Fou fou = new Fou(4, 4);
        List<int[]> possibilities = fou.checkPossibilities(4, 4);
        assertEquals(13, possibilities.size());
        assertTrue(containsArray(possibilities, new int[]{3, 3}));
        assertTrue(containsArray(possibilities, new int[]{5, 5}));
        assertTrue(containsArray(possibilities, new int[]{3, 5}));
        assertTrue(containsArray(possibilities, new int[]{5, 3}));
    }

    @Test
    public void checkPossibilitiesFromCornerPosition() {
        Fou fou = new Fou(0, 0);
        List<int[]> possibilities = fou.checkPossibilities(0, 0);
        assertEquals(7, possibilities.size());
        assertTrue(containsArray(possibilities, new int[]{1, 1}));
        assertTrue(containsArray(possibilities, new int[]{7, 7}));
    }

    @Test
    public void checkPossibilitiesFromEdgePosition() {
        Fou fou = new Fou(0, 4);
        List<int[]> possibilities = fou.checkPossibilities(0, 4);
        assertEquals(7, possibilities.size());
        assertTrue(containsArray(possibilities, new int[]{1, 3}));
        assertTrue(containsArray(possibilities, new int[]{3, 7}));
    }

    @Test
    public void checkPossibilitiesFromInvalidPosition() {
        Fou fou = new Fou(-1, 4);
        List<int[]> possibilities = fou.checkPossibilities(-1, 4);
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