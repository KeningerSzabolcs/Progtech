package beadando.connect4.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        // Initialize a new Board instance before each test
        board = new Board(6, 7); // Standard Connect4 dimensions
    }

    @Test
    void testMakeMoveValid() {
        assertTrue(board.makeMove(0, 1)); // Player 1 makes a move in column 0
        assertEquals(1, board.getGrid()[5][0]); // Check if the bottom cell is filled with player 1's marker
    }

    @Test
    void testMakeMoveInvalidColumn() {
        assertFalse(board.makeMove(-1, 1)); // Invalid column
        assertFalse(board.makeMove(7, 1));  // Invalid column
    }

    @Test
    void testMakeMoveColumnFull() {
        // Fill up column 0
        for (int i = 0; i < 6; i++) {
            board.makeMove(0, 1);
        }
        assertFalse(board.makeMove(0, 2)); // Column is full, should return false
    }

    @Test
    void testHorizontalWin() {
        // Create a horizontal win for player 1
        board.makeMove(0, 1);
        board.makeMove(1, 1);
        board.makeMove(2, 1);
        assertTrue(board.makeMove(3, 1)); // Last move to complete horizontal win

        assertTrue(board.winChecker()); // Check for win
    }

    @Test
    void testVerticalWin() {
        // Create a vertical win for player 2
        board.makeMove(0, 2);
        board.makeMove(0, 2);
        board.makeMove(0, 2);
        assertTrue(board.makeMove(0, 2)); // Last move to complete vertical win

        assertTrue(board.winChecker()); // Check for win
    }

    @Test
    void testDiagonalWinSimple() {
        board.makeMove(0, 1);
        board.makeMove(1, 2);
        board.makeMove(1, 1);
        board.makeMove(2, 2);
        board.makeMove(2, 2);
        board.makeMove(2, 1);
        board.makeMove(3, 2);
        board.makeMove(3, 2);
        board.makeMove(3, 2);
        board.makeMove(3, 1);

        board.printBoard();
        assertTrue(board.winChecker(), "Expected a diagonal win but got none.");
    }
    @Test
    void testPrintBoard() {

        assertDoesNotThrow(() -> board.printBoard());


    }
}