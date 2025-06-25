package com.ynov.echec.services;

import com.ynov.echec.models.Piece;

import java.util.ArrayList;
import java.util.List;

public class Pion extends Piece {

    public Pion(int x, int y) {
        super(x, y);
    }

    public void deplacer(int nouvelleX, int nouvelleY) {
        if (nouvelleX == x && (nouvelleY == y + 1 || (y == 1 && nouvelleY == y + 2))) {
            setPosition(nouvelleX, nouvelleY);
        } else {
            throw new IllegalArgumentException("Invalid move for a pawn.");
        }
    }

    public List<int[]> checkPossibilities(int positionX, int positionY) {
        List<int[]> possibilities = new ArrayList<>();

        // Vérification des coordonnées valides
        if (!validInput(positionX, positionY)) {
            return possibilities; // Retourne une liste vide si les coordonnées sont invalides
        }

        // le pion avance de un, une limite de 7 est posé pour al taille de l'échiquier
        if (positionY + 1 <= 7) {
            possibilities.add(new int[]{positionX, positionY + 1});
        }

        // le pion avance de deux, seulement depuis la position initiale (1)
        if (positionY == 1) {
            possibilities.add(new int[]{positionX, positionY + 2});
        }

        return possibilities;
    }

}
