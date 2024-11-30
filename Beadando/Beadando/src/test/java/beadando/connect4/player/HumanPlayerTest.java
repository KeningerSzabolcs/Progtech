package beadando.connect4.player;

import beadando.connect4.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanPlayerTest {

    private HumanPlayer humanPlayer;

    @BeforeEach
    void setUp() {
        String simulatedInput = "3\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        humanPlayer = new HumanPlayer("TestPlayer", 1);
    }

    @Test
    void testMakeMove() {
        int column = humanPlayer.makeMove();
        assertEquals(3, column);
    }
}
