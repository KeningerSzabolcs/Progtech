package beadando.connect4.player;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(String name, int playerNumber) {
        super(name, playerNumber);
        this.scanner = new Scanner(System.in);
    }

    public int makeMova() {
        System.out.println("Enter column (0-8): ");
        return this.scanner.nextInt();
    }
}
