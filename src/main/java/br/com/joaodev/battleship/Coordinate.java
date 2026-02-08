package br.com.joaodev.battleship;

import java.util.Locale;

record Coordinate(int row, int col) {
    static Coordinate parse(String s, int boardSize) {
        if (s == null) return null;
        s = s.trim().toUpperCase(Locale.ROOT);
        if (s.length() < 2 || s.length() > 3) return null;

        char letter = s.charAt(0);
        int row = letter - 'A';
        if (row < 0 || row >= boardSize) return null;

        int number;
        try {
            number = Integer.parseInt(s.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }

        if (number < 1 || number > boardSize) return null;

        return new Coordinate(row, number - 1);
    }
}
