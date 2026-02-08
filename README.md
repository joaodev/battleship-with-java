# Battleship Game in Java

This is a console-based implementation of the classic Battleship game written in Java using Maven.

## Description

In this game, you place your ships on a 10x10 grid and then take shots to try to sink all of them. The game ends when all ships have been sunk.

## Ships

The game includes the following ships:

- Aircraft Carrier (5 cells)
- Battleship (4 cells)
- Submarine (3 cells)
- Cruiser (3 cells)
- Destroyer (2 cells)

## How to Play

1. **Place Ships**: Enter the starting and ending coordinates for each ship (e.g., A1 A5 for a horizontal Aircraft Carrier).
   - Ships must be placed horizontally or vertically.
   - Ships cannot overlap or be placed adjacent to each other.

2. **Shoot**: Enter coordinates to shoot at the board (e.g., B3).
   - 'X' indicates a hit.
   - 'M' indicates a miss.
   - The game announces when a ship is sunk.

3. **Win**: Sink all ships to win the game.

## Requirements

- Java 25 or higher
- Maven

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Compile and run the game:

   ```bash
   mvn compile exec:java -Dexec.mainClass="br.com.joaodev.battleship.Main"
   ```

## Project Structure

- `src/main/java/br/com/joaodev/battleship/`: Source code
  - `Main.java`: Entry point and game loop
  - `Game.java`: Game logic
  - `Board.java`: Board representation
  - `Ship.java`: Ship class
  - `ShipType.java`: Ship types enumeration
  - `Coordinate.java`: Coordinate handling
  - `ShipPlacement.java`: Ship placement logic
  - `ShotResult.java`: Shot result enumeration

## License

This project is open-source. Feel free to use and modify it.
