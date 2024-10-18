package beadando.connect4.board;

/**
 * The Board class represents the Connect4 game board.
 * It supports moves and checking for wins.
 */
    public class Board {
        private final int[][] grid;
        private final int rows;
        private final int cols;

     /**
     * Initializes the board with given rows and columns.
     *
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public Board(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
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
        if (column < 0 || column >= cols) {
            return false;
        }
        for (int row = rows - 1; row >= 0; row--) {
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
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (grid[row][col] != 0 &&
                        grid[row][col] == grid[row][col + 1] &&
                        grid[row][col] == grid[row][col + 2] &&
                        grid[row][col] == grid[row][col + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin() {
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows - 3; row++) {
                if (grid[row][col] != 0 &&
                        grid[row][col] == grid[row + 1][col] &&
                        grid[row][col] == grid[row + 2][col] &&
                        grid[row][col] == grid[row + 3][col]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (grid[row][col] != 0 &&
                        grid[row][col] == grid[row + 1][col + 1] &&
                        grid[row][col] == grid[row + 2][col + 2] &&
                        grid[row][col] == grid[row + 3][col + 3]) {
                    return true;
                }
            }
        }
        for (int row = 3; row < rows; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (grid[row][col] != 0 &&
                        grid[row][col] == grid[row - 1][col + 1] &&
                        grid[row][col] == grid[row - 2][col + 2] &&
                        grid[row][col] == grid[row - 3][col + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
