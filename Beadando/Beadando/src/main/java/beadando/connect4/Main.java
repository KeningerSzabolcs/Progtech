package beadando.connect4;

import beadando.connect4.game.Game;

import java.util.Scanner;

public final class Main {
    private  Main() {
    }

    public static void main(final String[] args) {
        Game game = new Game();
        game.initializeDatabase();
        game.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Show Top Winners");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.print("Enter the number of winners to display: ");
                int numWinners = scanner.nextInt();
                scanner.nextLine();
                game.showTopWinners(numWinners);
            } else if (choice == 2) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
