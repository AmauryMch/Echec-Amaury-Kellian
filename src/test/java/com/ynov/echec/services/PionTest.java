package com.ynov.echec.services;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

public class PionTest {

    @Test
    void moveValidOneStepForward() {
        Pion pion = new Pion(0, 1);
        pion.move(0, 2);
        assertEquals(0, pion.getX());
        assertEquals(2, pion.getY());
    }

    @Test
    void moveValidTwoStepsForwardFromInitialPosition() {
        Pion pion = new Pion(0, 1);
        pion.move(0, 3);
        assertEquals(0, pion.getX());
        assertEquals(3, pion.getY());
    }

    @Test
    void moveInvalidTwoStepsForwardNotFromInitialPosition() {
        Pion pion = new Pion(0, 2);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.move(0, 4)
        );
        assertEquals("Invalid move for a pawn.", exception.getMessage());
    }

    @Test
    void moveInvalidSidewaysMove() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.move(1, 2)
        );
        assertEquals("Invalid move for a pawn.", exception.getMessage());
    }

    @Test
    void moveInvalidBackwardMove() {
        Pion pion = new Pion(0, 2);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.move(0, 1)
        );
        assertEquals("Invalid move for a pawn.", exception.getMessage());
    }

    @Test
    void moveInvalidMoveToSamePosition() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.move(0, 1)
        );
        assertEquals("Invalid move for a pawn.", exception.getMessage());
    }

    @Test
    void moveInvalidMoveOutOfBoard() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.move(0, 10)
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

    // TEST POUR move FROM TO

    @Test
    void moveFromToValidMove() {
        Pion pion = new Pion(0, 1);
        pion.moveFromTo(0, 1, 0, 2);
        assertEquals(0, pion.getX());
        assertEquals(2, pion.getY());
    }

    @Test
    void moveFromToInvalidOrigin() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.moveFromTo(1, 1, 0, 2)
        );
        assertEquals("The origin does not match the current position of the piece.", exception.getMessage());
    }

    @Test
    void moveFromToInvalidDestination() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.moveFromTo(0, 1, 1, 2)
        );
        assertEquals("The move is not valid for this piece.", exception.getMessage());
    }

    @Test
    void moveFromToOutOfBoardDestination() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.moveFromTo(0, 1, 0, 10)
        );
        assertEquals("The move is not valid for this piece.", exception.getMessage());
    }

    @Test
    void moveFromToSamePosition() {
        Pion pion = new Pion(0, 1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.moveFromTo(0, 1, 0, 1)
        );
        assertEquals("The move is not valid for this piece.", exception.getMessage());
    }

    @Test
    void moveFromToWithMockedCheckPossibilities() {
        Pion pion = Mockito.spy(new Pion(0, 1));

        // Mock de la méthode checkPossibilities
        // On Mock une position fictive et impossible pour tester le déplacement
        // Si checkPossibilities valide le déplacement, alors le test réussira
        List<int[]> mockedPossibilities = List.of(new int[]{0, 2}, new int[]{0, 3}, new int[]{0, 6});
        doReturn(mockedPossibilities).when(pion).checkPossibilities(0, 1);

        // Appel de la méthode avec une destination valide
        pion.moveFromTo(0, 1, 0, 6);

        // Vérification de la position mise à jour
        assertEquals(0, pion.getX());
        assertEquals(6, pion.getY());
        Mockito.verify(pion, Mockito.times(1)).checkPossibilities(0, 1);
    }

    @Test
    void moveFromToThrowException() {
        Pion pion = new Pion(0, 1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> pion.moveFromTo(0, 2, 0, 6)
        );

        assertEquals("The origin does not match the current position of the piece.", exception.getMessage());
    }
}
