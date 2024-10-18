package beadando.connect4.board;

public class Board {
    private static final int WINNING_COUNT = 4;
    private final int[][] grid;
    private final int rows;
    private final int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public boolean makeMove(int column, int player) {
        if (column >= 0 && column < this.cols) {
            for(int row = this.rows - 1; row >= 0; --row) {
                if (this.grid[row][column] == 0) {
                    this.grid[row][column] = player;
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public boolean winChecker() {
        return this.horzontalWinCheck() || this.verticalWinCheck() || this.diognalWinCheck();
    }

    private boolean diognalWinCheck() {
        int row;
        int col;
        for(row = 0; row < this.rows - 4 + 1; ++row) {
            for(col = 0; col < this.cols - 4 + 1; ++col) {
                if (this.grid[row][col] != 0 && this.grid[row][col] == this.grid[row + 1][col + 1] && this.grid[row][col] == this.grid[row + 2][col + 2] && this.grid[row][col] == this.grid[row + 3][col + 3]) {
                    return true;
                }
            }
        }

        for(row = 3; row < this.rows; ++row) {
            for(col = 0; col < this.cols - 4 + 1; ++col) {
                if (this.grid[row][col] != 0 && this.grid[row][col] == this.grid[row - 1][col + 1] && this.grid[row][col] == this.grid[row - 2][col + 2] && this.grid[row][col] == this.grid[row - 3][col + 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean verticalWinCheck() {
        for(int col = 0; col < this.cols; ++col) {
            for(int row = 0; row < this.rows - 4 + 1; ++row) {
                if (this.grid[row][col] != 0 && this.grid[row][col] == this.grid[row + 1][col] && this.grid[row][col] == this.grid[row + 2][col] && this.grid[row][col] == this.grid[row + 3][col]) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean horzontalWinCheck() {
        for(int row = 0; row < this.rows; ++row) {
            for(int col = 0; col < this.cols - 4 + 1; ++col) {
                if (this.grid[row][col] != 0 && this.grid[row][col] == this.grid[row][col + 1] && this.grid[row][col] == this.grid[row][col + 2] && this.grid[row][col] == this.grid[row][col + 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    public void printBoard() {
        for(int row = 0; row < this.rows; ++row) {
            for(int col = 0; col < this.cols; ++col) {
                System.out.println(this.grid[row][col] + 32);
            }

            System.out.println();
        }

    }
}
