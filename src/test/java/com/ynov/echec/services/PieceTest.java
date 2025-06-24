package com.ynov.echec.services;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PieceTest {

    @Test
    public void deplacerValidOneStepForward() {
        Pion pion = new Pion(0, 1);
        pion.deplacer(0, 2);
        assertEquals(2, pion.getY());
    }

    @Test
    public void deplacerValidTwoStepsForwardFromInitialPosition() {
        Pion pion = new Pion(0, 1);
        pion.deplacer(0, 3);
        assertEquals(3, pion.getY());
    }

    @Test
    public void deplacerInvalidTwoStepsForwardNotFromInitialPosition() {
        Pion pion = new Pion(0, 2);
        pion.deplacer(0, 4);
        assertEquals(2, pion.getY());
    }

    @Test
    public void deplacerInvalidSidewaysMove() {
        Pion pion = new Pion(0, 1);
        pion.deplacer(1, 2);
        assertEquals(1, pion.getY());
        assertEquals(0, pion.getX());
    }

    @Test
    public void deplacerInvalidBackwardMove() {
        Pion pion = new Pion(0, 2);
        pion.deplacer(0, 1);
        assertEquals(2, pion.getY());
    }
}