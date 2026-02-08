package br.com.joaodev.battleship;

import java.util.List;

public class Ship {
    private final ShipType shipType;
    private final List<Coordinate> cells;
    private boolean sunk;

    Ship(ShipType shipType, List<Coordinate> cells) {
        this.shipType = shipType;
        this.cells = List.copyOf(cells);
        this.sunk = false;
    }

    boolean getSunk() {
        return sunk;
    }

    void markSunk() {
        this.sunk = true;
    }

    boolean isSunk(Board board) {
        for (Coordinate cell : cells) {
            if (board.get(cell) != 'X') return false;
        }
        return true;
    }
}
