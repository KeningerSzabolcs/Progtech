package beadando.connect4.player;

import java.util.Scanner;

/**
 * HumanPlayer class, representing a human player in the Connect4 game.
 */
public class HumanPlayer extends Player {

    /**
     * Constructs a HumanPlayer object with a given name and player number.
     *
     * @param name         the player's name
     * @param playerNumber the player's number (1 or 2)
     */
    public HumanPlayer(final String name, final int playerNumber) {
        super(name, playerNumber);
    }

    /**
     * Makes a move by taking input from the human player.
     *
     * @return the column number where the player wants to place a piece
     */
    @Override
    public int makeMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter column (0-8): ");
        return scanner.nextInt();
    }

    public void askForName() {

    }
}
