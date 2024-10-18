package beadando.connect4.player;
import java.util.Random;

/**
 * ComputerPlayer class, representing a computer player in the Connect4 game.
 */
public class ComputerPlayer extends Player {

    /**
     * Constructs a ComputerPlayer object with a given name and player number.
     *
     * @param name         the player's name
     * @param playerNumber the player's number (1 or 2)
     */
    public ComputerPlayer(final String name, final int playerNumber) {
        super(name, playerNumber);
    }
    private final int randomSize = 9;

    /**
     * Makes a random move by the computer player.
     *
     * @return the column number where the computer wants to place a piece
     */
    @Override
    public int makeMove() {
        Random random = new Random();
        return random.nextInt(randomSize);
    }
}
