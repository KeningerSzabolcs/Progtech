package beadando.connect4.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComputerPlayerTest {

    @Test
    public void testMakeMove() {
        ComputerPlayer computerPlayer = new ComputerPlayer("Computer", 2);

        // A random számnak a 0 és 8 közötti intervallumban kell lennie
        int column = computerPlayer.makeMove();

        assertTrue(column >= 0 && column < 9, "The column should be between 0 and 8");
    }
}