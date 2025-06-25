package com.ynov.echec.services;

import com.ynov.echec.models.Piece;

import java.util.ArrayList;
import java.util.List;

public class Fou extends Piece {

    public Fou(int x, int y) {
        super(x, y);
    }

    public List<int[]> checkPossibilities(int x, int y) {
        List<int[]> possibilities = new ArrayList<>();

        // Vérification des coordonnées valides
        if (!validInput(x, y)) {
            return possibilities; // Retourne une liste vide si les coordonnées sont invalides
        }

        // Diagonal moves
        for (int i = -7; i <= 7; i++) {
            if (i != 0) {
                // Diagonal haut-gauche
                if (validInput(x + i, y + i)) {
                    possibilities.add(new int[]{x + i, y + i});
                }
                // Diagonal haut-droit
                if (validInput(x + i, y - i)) {
                    possibilities.add(new int[]{x + i, y - i});
                }
            }
        }

        return possibilities;
    }
}
