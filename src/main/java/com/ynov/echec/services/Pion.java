package com.ynov.echec.services;

public class Pion extends Piece{

    public Pion(int x, int y) {
        super(x, y);
    }

    public void deplacer(int nouvelleX, int nouvelleY) {
        if (nouvelleX == x && (nouvelleY == y + 1 || (y == 1 && nouvelleY == y + 2))) {
            setPosition(nouvelleX, nouvelleY);
            System.out.println("Déplacement valide: (" + x + ", " + y + ")");
        } else {
            System.out.println("Déplacement invalide pour un pion.");
        }
    }
}
