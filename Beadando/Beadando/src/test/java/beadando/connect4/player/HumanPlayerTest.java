package beadando.connect4.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanPlayerTest {

    private HumanPlayer humanPlayer;

    @BeforeEach
    void setUp() {
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        Scanner scanner = new Scanner(in);
        humanPlayer = new HumanPlayer("TestPlayer", 1);
    }

    @Test
    void testMakeMove() {
        int column = humanPlayer.makeMove();
        assertEquals(3, column);
    }
}
