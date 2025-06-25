package com.ynov.echec.services;

import com.ynov.echec.models.Piece;

import java.util.ArrayList;
import java.util.List;

public class Tour extends Piece {

    public Tour(int x, int y) {
        super(x, y);
    }

    public void deplacer(int nouvelleX, int nouvelleY) {
        if (!validInput(nouvelleX, nouvelleY)) {
            throw new IllegalArgumentException("Target position is out of the board.");
        }

        if (nouvelleX == this.x && nouvelleY == this.y) {
            throw new IllegalArgumentException("Cannot move to the same position.");
        }

        // Vérifie si le déplacement est horizontal ou vertical
        if (nouvelleX == this.x || nouvelleY == this.y) {
            setPosition(nouvelleX, nouvelleY);
        } else {
            throw new IllegalArgumentException("Invalid move for a rook.");
        }
    }

    public List<int[]> checkPossibilities(int x, int y) {
        List<int[]> possibilities = new ArrayList<>();

        // La tour n'est pas sur l'échiquier
        if (!validInput(x, y)) {
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
