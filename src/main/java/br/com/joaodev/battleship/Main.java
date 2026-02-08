package br.com.joaodev.battleship;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(10);

        placeShipsForPlayer(scanner, game);

        System.out.println("The game starts!");
        System.out.println();
        shootLoop(scanner, game);
    }

    private static void shootLoop(Scanner scanner, Game game) {
        while (true) {
            game.printBoard(true);
            System.out.println();
            System.out.println("Take a shot!");
            Coordinate shot = readShotCoordinate(scanner, game.getSize());
            ShotResult result = game.shoot(shot);

            if (result == ShotResult.MISS) {
                System.out.println("You missed!");
            } else if (game.justSankAsShip()) {
                if (game.allShipsSunk()) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                } else {
                    System.out.println("You sank a ship!");
                }
            } else {
                System.out.println("You hit a ship!");
            }
            System.out.println();
            game.printBoard(true);

            if (game.allShipsSunk()) {
                break;
            }
            System.out.println();
        }
    }

    private static void placeShipsForPlayer(Scanner scanner, Game game) {
        game.printBoard(false);

        for (ShipType shipType : ShipType.ORDER) {
            placeShipLoop(scanner, game, shipType);
        }
    }

    private static void placeShipLoop(Scanner scanner, Game game, ShipType shipType) {
        while (true) {
            System.out.printf("%nEnter the coordinates of the %s (%d cells):%n%n",
                    shipType.getDisplayName(), shipType.getLength());

            String[] t = scanner.nextLine().trim().toUpperCase(Locale.ROOT).split("\\s+");
            if (t.length != 2) {
                printWrongLocation();
                continue;
            }

            Coordinate a = Coordinate.parse(t[0], game.getSize());
            Coordinate b = Coordinate.parse(t[1], game.getSize());
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

            if (game.isTooClose(placement.getCells())) {
                System.out.println();
                System.out.println("Error! You placed it too close to another one. Try again:");
                System.out.println();
                continue;
            }

            game.placeShip(shipType, placement.getCells());
            System.out.println();
            game.printBoard(false);
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
