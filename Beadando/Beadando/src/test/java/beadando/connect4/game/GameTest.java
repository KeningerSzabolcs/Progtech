package beadando.connect4.game;

import beadando.connect4.board.Board;
import beadando.connect4.player.ComputerPlayer;
import beadando.connect4.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameTest {

    private Game game;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;

    @BeforeEach
    void setUp() {
        humanPlayer = mock(HumanPlayer.class);
        computerPlayer = mock(ComputerPlayer.class);
        Board board = new Board(9, 9);
        game = new Game();
    }

    @Test
    void testGameLoop() {
        when(humanPlayer.makeMove()).thenReturn(3);
        when(humanPlayer.getPlayerNumber()).thenReturn(1);
        when(computerPlayer.makeMove()).thenReturn(4);
        when(computerPlayer.getPlayerNumber()).thenReturn(2);
        game.start();
        verify(humanPlayer, times(1)).makeMove();
        verify(computerPlayer, times(1)).makeMove();
    }
}
