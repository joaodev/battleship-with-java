package br.com.joaodev.battleship;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Ship> ships = new ArrayList<>();
    private boolean sankOnLastShot = false;

    Game(int size) {
        this.board = new Board(size);
    }

    int getSize() {
        return board.getSize();
    }

    void printBoard(boolean fog) {
        board.print(fog);
    }

    void placeShip(ShipType shipType, List<Coordinate> cells) {
        ships.add(new Ship(shipType, cells));
        board.place(cells);
    }

    boolean isTooClose(List<Coordinate> newCells) {
        return board.isTooClose(newCells);
    }

    ShotResult shoot(Coordinate target) {
        setSankOnLastShot(false);
        char cur = board.get(target);

        if (cur == 'X') return ShotResult.HIT;
        if (cur == 'M') return ShotResult.MISS;

        if (cur == 'O') {
            board.set(target, 'X');
            for (Ship ship : ships) {
                if (!ship.getSunk() && ship.isSunk(board)) {
                    ship.markSunk();
                    setSankOnLastShot(true);
                    break;
                }
            }
            return ShotResult.HIT;
        } else {
            board.set(target, 'M');
            return ShotResult.MISS;
        }
    }

    boolean justSankAsShip() {
        return getSankOnLastShot();
    }

    boolean allShipsSunk() {
        return ships.stream().allMatch(Ship::getSunk);
    }

    public boolean getSankOnLastShot() {
        return sankOnLastShot;
    }

    public void setSankOnLastShot(boolean sankOnLastShot) {
        this.sankOnLastShot = sankOnLastShot;
    }
}
