package beadando.connect4.game;

import beadando.connect4.board.Board;
import beadando.connect4.player.ComputerPlayer;
import beadando.connect4.player.HumanPlayer;
import java.util.Scanner;

public class Game {
    private final Board board = new Board(9, 9);
    private final HumanPlayer humanPlayer = new HumanPlayer("Human", 1);
    private final ComputerPlayer computerPlayer = new ComputerPlayer("Computer", 2);
    private final boolean isGameRunning = true;

    public Game() {
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while(this.isGameRunning) {
            this.board.printBoard();
            int column = this.humanPlayer.makeMova();
            if (!this.board.makeMove(column, this.humanPlayer.playerNumber)) {
                System.out.println("Invalid move. Try again.");
            } else {
                if (this.board.winChecker()) {
                    this.board.printBoard();
                    System.out.println("Human player wins!");
                    break;
                }

                column = this.computerPlayer.makeMova();
                if (this.board.makeMove(column, this.computerPlayer.playerNumber) && this.board.winChecker()) {
                    this.board.printBoard();
                    System.out.println("Computer player wins!");
                    break;
                }
            }
        }

        scanner.close();
    }
}
