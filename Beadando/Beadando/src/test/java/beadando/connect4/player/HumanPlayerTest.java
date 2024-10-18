package beadando.connect4.player;
import beadando.connect4.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanPlayerTest {

    private HumanPlayer humanPlayer;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        mockScanner = Mockito.mock(Scanner.class);
        humanPlayer = new HumanPlayer("Player1", 1) {
            @Override
            public int makeMove() {
                return mockScanner.nextInt();
            }
        };
    }

    @Test
    public void testMakeMove() {
        Mockito.when(mockScanner.nextInt()).thenReturn(3);

        int column = humanPlayer.makeMove();
        assertEquals(3, column);
    }
}
