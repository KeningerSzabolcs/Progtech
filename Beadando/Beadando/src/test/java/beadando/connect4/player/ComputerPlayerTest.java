package beadando.connect4.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import beadando.connect4.player.ComputerPlayer;

public class ComputerPlayerTest {

    @Test
    public void testMakeMove() {
        ComputerPlayer computerPlayer = new ComputerPlayer("Computer", 2);

        for (int i = 0; i < 100; i++) {
            int move = computerPlayer.makeMove();
            assertTrue(move >= 0 && move <= 8, "A mozdulat nem a megfelelő tartományban van: " + move);
        }
    }
}