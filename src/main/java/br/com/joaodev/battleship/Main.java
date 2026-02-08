package br.com.joaodev.battleship;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board(10);
        board.print();

        for (ShipType shipType : ShipType.ORDER) {
            while (true) {
                System.out.printf("%nEnter the coordinates of the %s (%d cells):%n%n",
                        shipType.displayName(), shipType.length());

                String[] t = scanner.nextLine().trim().toUpperCase(Locale.ROOT).split("\\s+");

                if (t.length != 2) {
                    printErrorLocation();
                    continue;
                }

                Coordinate a = Coordinate.parse(t[0], board.size);
                Coordinate b = Coordinate.parse(t[1], board.size);

                if (a == null || b == null) {
                    printErrorLocation();
                    continue;
                }

                ShipPlacement placement;
                try {
                    placement = ShipPlacement.from(a, b);
                } catch (IllegalArgumentException e) {
                    printErrorLocation();
                    continue;
                }

                if (placement.length() != shipType.length()) {
                    System.out.println();
                    System.out.printf("Error! Wrong length of the %s! Try again:%n", shipType.displayName());
                    System.out.println();
                    continue;
                }

                if (board.isTooClose(placement.cells())) {
                    System.out.println();
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.println();
                    continue;
                }

                board.place(placement.cells());
                System.out.println();
                board.print();
                break;
            }
        }

        scanner.close();
    }

    private static void printErrorLocation() {
        System.out.println();
        System.out.println("Error! Wrong ship location! Try again:");
        System.out.println();
    }
}

