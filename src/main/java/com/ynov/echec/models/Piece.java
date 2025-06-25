package com.ynov.echec.models;

public class Piece {
    protected int x; // Position en colonne (0-7)
    protected int y; // Position en ligne (0-7)

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean validInput(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }
}