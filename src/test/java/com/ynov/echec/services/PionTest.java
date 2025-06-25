package com.ynov.echec.services;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

public class PionTest {

    @Test
    void deplacerValidOneStepForward() {
        Pion pion = new Pion(0, 1);
        pion.deplacer(0, 2);
        assertEquals(0, pion.getX());
        assertEquals(2, pion.getY());
    }

    @Test
    void deplacerValidTwoStepsForwardFromInitialPosition() {
        Pion pion = new Pion(0, 1);
        pion.deplacer(0, 3);
        assertEquals(0, pion.getX());
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

    // TEST POUR DEPLACER FROM TO

    @Test
    void deplacerFromToValidMove() {
        Pion pion = new Pion(0, 1);
        pion.deplacerFromTo(0, 1, 0, 2);
        assertEquals(0, pion.getX());
        assertEquals(2, pion.getY());
    }

    @Test
    void deplacerFromToInvalidOrigin() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacerFromTo(1, 1, 0, 2)
        );
        assertEquals("The origin does not match the current position of the piece.", exception.getMessage());
    }

    @Test
    void deplacerFromToInvalidDestination() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacerFromTo(0, 1, 1, 2)
        );
        assertEquals("The move is not valid for this piece.", exception.getMessage());
    }

    @Test
    void deplacerFromToOutOfBoardDestination() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacerFromTo(0, 1, 0, 10)
        );
        assertEquals("The move is not valid for this piece.", exception.getMessage());
    }

    @Test
    void deplacerFromToSamePosition() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacerFromTo(0, 1, 0, 1)
        );
        assertEquals("The move is not valid for this piece.", exception.getMessage());
    }

    @Test
    void deplacerFromToWithMockedCheckPossibilities() {
        Pion pion = Mockito.spy(new Pion(0, 1));

        // Mock de la méthode checkPossibilities
        // On Mock une position fictive et impossible pour tester le déplacement
        // Si checkPossibilities valide le déplacement, alors le test réussira
        List<int[]> mockedPossibilities = List.of(new int[]{0, 2}, new int[]{0, 3}, new int[]{0, 6});
        doReturn(mockedPossibilities).when(pion).checkPossibilities(0, 1);

        // Appel de la méthode avec une destination valide
        pion.deplacerFromTo(0, 1, 0, 6);

        // Vérification de la position mise à jour
        assertEquals(0, pion.getX());
        assertEquals(6, pion.getY());
        Mockito.verify(pion, Mockito.times(1)).checkPossibilities(0, 1);
    }

    @Test
    void deplacerFromToThrowException() {
        Pion pion = new Pion(0, 1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.deplacerFromTo(0, 2, 0, 6)
        );

        assertEquals("The origin does not match the current position of the piece.", exception.getMessage());
    }
}
