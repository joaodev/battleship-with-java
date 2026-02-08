package br.com.joaodev.battleship;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(10);
        board.print();

        for (ShipType shipType : ShipType.ORDER) {
            placeShipLoop(scanner, board, shipType);
        }

        System.out.println();
        System.out.println("The game starts!");
        System.out.println();
        board.print();
        System.out.println();
        System.out.println("Take a shot!");
        System.out.println();

        Coordinate target = readShotCoordinate(scanner, board.getSize());
        board.applyShot(target);

        System.out.println();
        board.print();
        System.out.println();

        if (board.wasHitLastShot()) {
            System.out.println("You hit a ship!");
        } else {
            System.out.println("You missed!");
        }
    }

    private static void placeShipLoop(Scanner scanner, Board board, ShipType shipType) {
        while (true) {
            System.out.printf("%nEnter the coordinates of the %s (%d cells):%n%n",
                    shipType.getDisplayName(), shipType.getLength());

            String[] t = scanner.nextLine().trim().toUpperCase(Locale.ROOT).split("\\s+");
            if (t.length != 2) {
                printWrongLocation();
                continue;
            }

            Coordinate a = Coordinate.parse(t[0], board.getSize());
            Coordinate b = Coordinate.parse(t[1], board.getSize());
            if (a == null || b == null) {
                printWrongLocation();
                continue;
            }

            ShipPlacement placement;
            try {
                placement = ShipPlacement.from(a, b);
            } catch (IllegalArgumentException e) {
                printWrongLocation();
                continue;
            }

            if (placement.getLength() != shipType.getLength()) {
                System.out.println();
                System.out.printf("Error! Wrong length of the %s! Try again:%n", shipType.getDisplayName());
                System.out.println();
                continue;
            }

            if (board.isTooClose(placement.getCells())) {
                System.out.println();
                System.out.println("Error! You placed it too close to another one. Try again:");
                System.out.println();
                continue;
            }

            board.place(placement.getCells());
            System.out.println();
            board.print();
            break;
        }
    }

    private static Coordinate readShotCoordinate(Scanner scanner, int boardSize) {
        while (true) {
            String s = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            Coordinate c = Coordinate.parse(s, boardSize);

            if (c == null) {
                System.out.println();
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                System.out.println();
                continue;
            }

            return c;
        }
    }

    private static void printWrongLocation() {
        System.out.println();
        System.out.println("Error! Wrong ship location! Try again:");
        System.out.println();
    }
}
