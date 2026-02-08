package br.com.joaodev.battleship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShipPlacement {
    private final List<Coordinate> cells;

    private ShipPlacement(List<Coordinate> cells) {
        this.cells = cells;
    }

    static ShipPlacement from (Coordinate a, Coordinate b) {
        boolean sameRow = a.row() == b.row();
        boolean sameCol = a.col() == b.col();
        if (!sameRow && !sameCol) throw new IllegalArgumentException("diagonal");

        int dr = Integer.compare(b.row(), a.row());
        int dc = Integer.compare(b.col(), a.col());

        List<Coordinate> list = new ArrayList<>();
        for (int r = a.row(), c = a.col(); ; r += dr, c += dc) {
            list.add(new Coordinate(r, c));
            if (r == b.row() && c == b.col()) break;
        }

        return new ShipPlacement(Collections.unmodifiableList(list));
    }

    int length() {
        return cells.size();
    }

    List<Coordinate> cells() {
        return cells;
    }
}
