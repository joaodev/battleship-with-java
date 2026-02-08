package br.com.joaodev.battleship;

import java.util.Arrays;
import java.util.List;

public class Board {

    final int size;
    final char[][] grid;

    Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
        for (char[] row : grid) {
            Arrays.fill(row, '~');
        }
    }

    int getSize() {
        return size;
    }

    void print(boolean fog) {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) System.out.print(i + (i == size ? "" : " "));
        System.out.println();

        for (int r = 0; r < size; r++) {
            System.out.print((char) ('A' + r) + " ");
            for (int c = 0; c < size; c++) {
                char ch = grid[r][c];
                if (fog && ch == 'O') ch = '~';
                System.out.print(ch + (c == size - 1 ? "" : " "));
            }
            System.out.println();
        }
    }

    void place(List<Coordinate> cells) {
        for (Coordinate cell : cells) {
            grid[cell.row()][cell.col()] = 'O';
        }
    }

    boolean isTooClose(List<Coordinate> newCells) {
        for (Coordinate cell : newCells) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int rr = cell.row() + dr;
                    int cc = cell.col() + dc;
                    if (inBounds(rr, cc) && grid[rr][cc] == 'O') return true;
                }
            }
        }
        return false;
    }

    char get(Coordinate cell) {
        return grid[cell.row()][cell.col()];
    }

    void set(Coordinate cell, char ch) {
        grid[cell.row()][cell.col()] = ch;
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }
}
