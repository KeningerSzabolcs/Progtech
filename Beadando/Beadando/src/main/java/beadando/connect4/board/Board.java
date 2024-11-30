package beadando.connect4.board;

/**
 * The Board class represents the Connect4 game board.
 * It supports moves and checking for wins.
 */
public final class Board {
    private final int[][] grid;
    private final int numRows;
    private final int numCols;

    private static final int CONNECT_LENGTH = 4;
    private final int three = 3;

    /**
     * Initializes the board with given rows and columns.
     *
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public Board(final int rows, final int cols) {
        this.numRows = rows;
        this.numCols = cols;
        this.grid = new int[rows][cols];
    }

    public int[][] getGrid() {
        return grid;
    }

    /**
     * Makes a move for the given player in the specified column.
     *
     * @param column the column to make a move in
     * @param player the player's number (1 or 2)
     * @return true if the move is successful, false otherwise
     */
    public boolean makeMove(final int column, final int player) {
        if (column < 0 || column >= numCols) {
            return false;
        }
        for (int row = numRows - 1; row >= 0; row--) {
            if (grid[row][column] == 0) {
                grid[row][column] = player;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a win on the board.
     *
     * @return true if there is a win, false otherwise
     */
    public boolean winChecker() {
        return checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin();
    }

    private boolean checkHorizontalWin() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols - CONNECT_LENGTH + 1; col++) {
                if (grid[row][col] != 0
                        && grid[row][col] == grid[row][col + 1]
                        && grid[row][col] == grid[row][col + 2]
                        && grid[row][col] == grid[row][col + three]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin() {
        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows - CONNECT_LENGTH + 1; row++) {
                if (grid[row][col] != 0
                        && grid[row][col] == grid[row + 1][col]
                        && grid[row][col] == grid[row + 2][col]
                        && grid[row][col] == grid[row + three][col]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        for (int row = 0; row < numRows - CONNECT_LENGTH + 1; row++) {
            for (int col = 0; col < numCols - CONNECT_LENGTH + 1; col++) {
                if (grid[row][col] != 0
                        && grid[row][col] == grid[row + 1][col + 1]
                        && grid[row][col] == grid[row + 2][col + 2]
                        && grid[row][col] == grid[row + three][col + three]) {
                    return true;
                }
            }
        }
        for (int row = CONNECT_LENGTH - 1; row < numRows; row++) {
            for (int col = 0; col < numCols - CONNECT_LENGTH + 1; col++) {
                if (grid[row][col] != 0
                        && grid[row][col] == grid[row - 1][col + 1]
                        && grid[row][col] == grid[row - 2][col + 2]
                        && grid[row][col] == grid[row - three][col + three]) {
                    return true;
                }
            }
        }

        return false;
    }

    public void printBoard() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == 0) {
                    System.out.print(". ");
                } else if (grid[row][col] == 1) {
                    System.out.print("Y ");
                } else if (grid[row][col] == 2) {
                    System.out.print("R ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
