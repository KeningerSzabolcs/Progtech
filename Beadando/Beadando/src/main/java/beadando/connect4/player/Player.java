package beadando.connect4.player;

/**
 * Abstract class representing a player in the Connect4 game.
 * It defines common properties and behaviors for players.
 */
public abstract class Player {
    private final String name;
    private final int playerNumber;


    /**
     * Gets the player number.
     *
     * @return the player's number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Constructor for the Player class.
     *
     * @param playerName         the player's name
     * @param number the player's number (1 or 2)
     */
    protected Player(final String playerName, final int number) {
        this.name = playerName;
        this.playerNumber = number;
    }

    /**
     * Abstract method for making a move.
     * Each player type will implement their own logic for making a move.
     *
     * @return the column where the player wants to place a piece
     */
    public abstract int makeMove();

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player as a {@code String}.
     */
    public String getName() {
        return name;
    }
}

