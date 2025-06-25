package com.ynov.echec.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CavalierTest {

    @Test
    void validMoveAndUpdatesPosition() {
        Cavalier cavalier = new Cavalier(4, 4);
        cavalier.deplacer(6, 5);
        Assertions.assertEquals(6, cavalier.getX());
        Assertions.assertEquals(5, cavalier.getY());
    }

    @Test
    void otherValidMoveAndUpdatesPosition() {
        Cavalier cavalier = new Cavalier(4, 4);
        cavalier.deplacer(5, 6);
        Assertions.assertEquals(6, cavalier.getX());
        Assertions.assertEquals(5, cavalier.getY());
    }

    @Test
    void invalidMoveDoesNotUpdatePosition() {
        Cavalier cavalier = new Cavalier(4, 4);
        cavalier.deplacer(5, 5);
        Assertions.assertEquals(4, cavalier.getX());
        Assertions.assertEquals(4, cavalier.getY());
    }

    @Test
    void checkPossibilitiesReturnsValidMovesWithinBounds() {
        Cavalier cavalier = new Cavalier(4, 4);
        List<int[]> possibilities = cavalier.checkPossibilities(4, 4);

        Assertions.assertTrue(
                possibilities.stream().anyMatch(arr -> arr[0] == 6 && arr[1] == 5)
        );
        Assertions.assertTrue(
                possibilities.stream().anyMatch(arr -> arr[0] == 5 && arr[1] == 6)
        );
        Assertions.assertTrue(
                possibilities.stream().anyMatch(arr -> arr[0] == 2 && arr[1] == 3)
        );
        Assertions.assertTrue(
                possibilities.stream().anyMatch(arr -> arr[0] == 3 && arr[1] == 2)
        );
    }

    @Test
    void checkPossibilitiesExcludesOutOfBoundsMoves() {
        Cavalier cavalier = new Cavalier(0, 0);
        List<int[]> possibilities = cavalier.checkPossibilities(0, 0);
        Assertions.assertFalse(possibilities.contains(new int[]{-2, -1}));
        Assertions.assertFalse(possibilities.contains(new int[]{-1, -2}));
    }
}