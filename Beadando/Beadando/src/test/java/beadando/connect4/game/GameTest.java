package beadando.connect4.game;

import beadando.connect4.board.Board;
import beadando.connect4.player.ComputerPlayer;
import beadando.connect4.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GameTest {

    private Game game;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private Board board;

    @BeforeEach
    public void setUp() {
        humanPlayer = Mockito.mock(HumanPlayer.class);
        computerPlayer = Mockito.mock(ComputerPlayer.class);
        board = Mockito.mock(Board.class);
        game = new Game();
    }

    @Test
    public void testHumanWins() {
        // Setup the mock behavior
        when(humanPlayer.makeMove()).thenReturn(0); // Human player chooses column 0
        when(board.makeMove(0, 1)).thenReturn(true); // Move is successful
        when(board.winChecker()).thenReturn(true); // Human player wins

        // Start the game
        game.start();

        // Verify that the correct methods were called
        verify(board).printBoard();
        verify(board).makeMove(0, 1);
        verify(board).winChecker();
        // Add any additional assertions or verifications as needed
    }

    @Test
    public void testComputerWins() {
        when(humanPlayer.makeMove()).thenReturn(0);
        when(board.makeMove(0, 1)).thenReturn(true);
        when(humanPlayer.getPlayerNumber()).thenReturn(1);

        when(computerPlayer.makeMove()).thenReturn(1); // Computer chooses column 1
        when(board.makeMove(1, 2)).thenReturn(true); // Move is successful
        when(board.winChecker()).thenReturn(true); // Computer player wins

        game.start();

        verify(board).printBoard();
        verify(board).makeMove(0, 1);
        verify(board).makeMove(1, 2);
        verify(board).winChecker();
    }

    @Test
    public void testInvalidMove() {
        when(humanPlayer.makeMove()).thenReturn(0);
        when(board.makeMove(0, 1)).thenReturn(false); // Invalid move

        game.start();

        verify(board).printBoard();
        verify(board).makeMove(0, 1);
        verify(board, never()).winChecker(); // Win checker should not be called
    }
}
