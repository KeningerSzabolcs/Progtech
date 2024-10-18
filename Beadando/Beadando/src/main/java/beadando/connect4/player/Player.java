package beadando.connect4.player;

/**
 * Abstract class representing a player in the Connect4 game.
 * It defines common properties and behaviors for players.
 */
public abstract class Player {
    public final String name;
    public final int playerNumber;

    /**
     * Constructor for the Player class.
     *
     * @param name         the player's name
     * @param playerNumber the player's number (1 or 2)
     */
    protected Player(final String name,final int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
    }

    /**
     * Abstract method for making a move.
     * Each player type will implement their own logic for making a move.
     *
     * @return the column where the player wants to place a piece
     */
    public abstract int makeMove();
}
