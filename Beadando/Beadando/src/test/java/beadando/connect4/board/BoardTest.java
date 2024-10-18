package beadando.connect4.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(6, 7);
    }

    @Test
    public void testInitialBoardState() {
        int[][] expectedGrid = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };

        assertArrayEquals(expectedGrid, board.getGrid());
    }

    @Test
    public void testMakeMove() {
        assertTrue(board.makeMove(3, 1));
        assertEquals(1, board.getGrid()[5][3]);
    }

    @Test
    public void testColumnFull() {
        for (int i = 0; i < 6; i++) {
            board.makeMove(3, 1);
        }
        assertFalse(board.makeMove(3, 2));
    }

    @Test
    public void testHorizontalWin() {
        board.makeMove(0, 1);
        board.makeMove(1, 1);
        board.makeMove(2, 1);
        assertTrue(board.makeMove(3, 1));
        assertTrue(board.winChecker());
    }

    @Test
    public void testVerticalWin() {
        for (int i = 0; i < 4; i++) {
            board.makeMove(0, 1);
        }
        assertTrue(board.winChecker());
    }

    @Test
    public void testDiagonalWin() {
        board.makeMove(0, 1);
        board.makeMove(1, 1);
        board.makeMove(1, 2);
        board.makeMove(2, 2);
        board.makeMove(2, 1);
        board.makeMove(2, 3);
        board.makeMove(3, 2);
        board.makeMove(3, 1);
        board.makeMove(3, 3);
        board.makeMove(3, 0);
        assertTrue(board.winChecker());
    }

    @Test
    public void testPrintBoard() {
        assertDoesNotThrow(() -> board.printBoard());
    }
}