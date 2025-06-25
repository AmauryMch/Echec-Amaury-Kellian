package com.ynov.echec.services;

import com.ynov.echec.models.Piece;

import java.util.ArrayList;
import java.util.List;

public class Tour extends Piece {

    public Tour(int x, int y) {
        super(x, y);
    }

    public List<int[]> checkPossibilities(int x, int y) {
        List<int[]> possibilities = new ArrayList<>();

        // La tour n'est pas sur l'échiquier
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return possibilities;
        }

        // Horizontal moves
        for (int i = 0; i < 8; i++) {
            if (i != x) {
                possibilities.add(new int[]{i, y});
            }
        }

        // Vertical moves
        for (int j = 0; j < 8; j++) {
            if (j != y) {
                possibilities.add(new int[]{x, j});
            }
        }

        return possibilities;
    }
}
