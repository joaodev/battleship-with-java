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

    void print() {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) System.out.print(i + (i == size ? "" : " "));
        System.out.println();

        for (int r = 0; r < size; r++) {
            System.out.print((char) ('A' + r) + " ");
            for (int c = 0; c < size; c++) {
                System.out.print(grid[r][c] + (c == size - 1 ? "" : " "));
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
                    if (rr >= 0 && rr < size && cc >= 0 && cc < size) {
                        if (grid[rr][cc] == 'O') return true;
                    }
                }
            }
        }
        return false;
    }
}
