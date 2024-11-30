package beadando.connect4.player;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import beadando.connect4.player.Player;

public class PlayerTest {

    @Test
    void testGetPlayerNumber() {
        Player mockPlayer = mock(Player.class);

        when(mockPlayer.getPlayerNumber()).thenReturn(1);

        int playerNumber = mockPlayer.getPlayerNumber();

        assertEquals(1, playerNumber, "A játékos számának 1-nek kell lennie.");
    }

    @Test
    void testGetName() {
        Player mockPlayer = mock(Player.class);

        when(mockPlayer.getName()).thenReturn("John Doe");

        String name = mockPlayer.getName();

        assertEquals("John Doe", name, "A játékos neve 'John Doe' kell, hogy legyen.");
    }

    @Test
    void testPlayerConstructor() {
        Player player = new Player("Alice", 1) {
            @Override
            public int makeMove() {
                return 0;
            }
        };

        assertEquals("Alice", player.getName(), "A játékos neve 'Alice' kell, hogy legyen.");
        assertEquals(1, player.getPlayerNumber(), "A játékos száma 1 kell, hogy legyen.");
    }
}
