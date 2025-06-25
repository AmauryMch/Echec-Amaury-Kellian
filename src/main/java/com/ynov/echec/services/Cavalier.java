package com.ynov.echec.services;

import com.ynov.echec.models.Piece;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Piece {

    public Cavalier(int x, int y) {
        super(x, y);
    }

    public void deplacer(int newX, int newY) {
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
            setPosition(newX, newY);
        } else {
            throw new IllegalArgumentException("Invalid move for a knight.");
        }
    }

    public List<int[]> checkPossibilities(int positionX, int positionY) {
        List<int[]> possibilities = new ArrayList<>();
        int[][] moves = {
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
        };

        for (int[] move : moves) {
            int nx = positionX + move[0];
            int ny = positionY + move[1];
            if (validInput(nx, ny)) {
                possibilities.add(new int[]{nx, ny});
            }
        }
        return possibilities;
    }
}
