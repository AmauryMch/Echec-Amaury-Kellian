package com.ynov.echec.services;

import com.ynov.echec.models.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PieceTest {

    @Test
    void validInputReturnsTrueForValidCoordinates() {
        Piece piece = new Piece(0, 0);
        assertTrue(piece.validInput(4, 4));
        assertTrue(piece.validInput(7, 7));
        assertTrue(piece.validInput(0, 0));
    }

    @Test
    void validInputReturnsFalseForNegativeCoordinates() {
        Piece piece = new Piece(0, 0);
        assertFalse(piece.validInput(-1, 4));
        assertFalse(piece.validInput(4, -1));
        assertFalse(piece.validInput(-1, -1));
    }

    @Test
    void validInputReturnsFalseForCoordinatesOutOfBounds() {
        Piece piece = new Piece(0, 0);
        assertFalse(piece.validInput(8, 4));
        assertFalse(piece.validInput(4, 8));
        assertFalse(piece.validInput(8, 8));
    }
}