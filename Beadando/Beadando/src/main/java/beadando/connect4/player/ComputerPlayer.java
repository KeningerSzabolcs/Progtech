package beadando.connect4.player;
import java.util.Random;

public class ComputerPlayer extends Player {
    private final Random random = new Random();

    public ComputerPlayer(String name, int playerNumber) {
        super(name, playerNumber);
    }

    public int makeMova() {
        return this.random.nextInt(9);
    }
}
