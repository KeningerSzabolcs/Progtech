package beadando.connect4.game;

import beadando.connect4.board.Board;
import beadando.connect4.player.ComputerPlayer;
import beadando.connect4.player.HumanPlayer;

/**
 * The Game class orchestrates the Connect4 game between a human player
 * and a computer player on a board.
 */
public class Game {
    private final Board board = new Board(9, 9);
    private final HumanPlayer humanPlayer = new HumanPlayer("Human", 1);
    private final ComputerPlayer computerPlayer =
            new ComputerPlayer("Computer", 2);
    private final boolean isGameRunning = true;

    /**
     * Starts the game loop where the human and computer players
     * take turns until one wins or the game ends.
     */
    public void start() {
        while (this.isGameRunning) {
            this.board.printBoard();
            int column = this.humanPlayer.makeMove();
            if (!this.board.makeMove(column,
                    this.humanPlayer.getPlayerNumber())) {
                System.out.println("Invalid move. Try again.");
            } else {
                if (board.winChecker()) {
                    board.printBoard();
                    System.out.println("Human player wins!");
                    break;
                }

                column = this.computerPlayer.makeMove();
                if (this.board.makeMove(column,
                        this.computerPlayer.getPlayerNumber())
                        && this.board.winChecker()) {
                    this.board.printBoard();
                    System.out.println("Computer player wins!");
                    break;
                }
            }
        }


    }
}
