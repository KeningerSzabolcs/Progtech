package beadando.connect4.game;

import beadando.connect4.board.Board;
import beadando.connect4.player.ComputerPlayer;
import beadando.connect4.player.HumanPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


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
    private final int nine = 9;
    private static final String DB_URL = "jdbc:sqlite:player_data.db";
    private static final String DB_USER = "your_db_user";
    private static final String DB_PASSWORD = "your_db_password";

    /**
     * Starts the game loop where the human and computer players
     * take turns until one wins or the game ends.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Adja meg a játékos nevét:");
        String playerName = scanner.nextLine();
        HumanPlayer humanPlayer = new HumanPlayer(playerName, 1);
        System.out.println("Szeretne egy korábbi játékot betölteni? (i/n)");
        String loadGame = scanner.nextLine();
        if (loadGame.equalsIgnoreCase("i")) {
            System.out.println("Adja meg a fájl nevét:");
            String fileName = scanner.nextLine();
            loadFromFile(fileName);
        } else {
            System.out.println("Üres pályával indul a játék.");
        }

        while (this.isGameRunning) {
            this.board.printBoard();
            int column = this.humanPlayer.makeMove();
            if (!this.board.makeMove(column,
                    this.humanPlayer.getPlayerNumber())) {
                System.out.println("Invalid move. Try again.");
            } else {
                if (board.winChecker()) {
                    board.printBoard();
                    System.out.println(humanPlayer.getName() + " wins!");
                    updatePlayerWins(humanPlayer.getName());
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
            System.out.println("Szeretné menteni a játékot? (i/n)");
            String saveGame = scanner.nextLine();
            if (saveGame.equalsIgnoreCase("i")) {
                System.out.println("Adja meg a fájl nevét:");
                String fileName = scanner.nextLine();
                saveToFile(fileName);
                System.out.println("Játék elmentve.");
            }
        }


    }

    /**
     * Loads the game state from a specified file.
     *
     * @param fileName the name of the file to load the game state from
     */
    public void loadFromFile(final String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            for (int row = 0; row < nine; row++) {
                for (int col = 0; col < nine; col++) {
                    if (scanner.hasNextInt()) {
                        board.getGrid()[row][col] = scanner.nextInt();
                    }
                }
            }
            System.out.println("Játék betöltve a fájlból.");
        } catch (FileNotFoundException e) {
            System.out.println("Nem található a fájl: " + fileName);
        }
    }

    /**
     * Saves the current game state to a specified file.
     *
     * @param fileName the name of the file to save the game state to
     */
    public void saveToFile(final String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (int row = 0; row < nine; row++) {
                for (int col = 0; col < nine; col++) {
                    writer.print(board.getGrid()[row][col] + " ");
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nem lehet létrehozni a fájlt: " + fileName);
        }
    }

    public void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS players (name VARCHAR(255) PRIMARY KEY, wins INT)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void showTopWinners(int numWinners) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT name, wins FROM players ORDER BY wins DESC")) {
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            System.out.println("Top " + numWinners + " Winners:");
            while (rs.next() && counter < numWinners) {
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                System.out.println(name + " - " + wins + " wins");
                counter++;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    private void updatePlayerWins(String playerName) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("INSERT OR REPLACE INTO players (name, wins) VALUES (?, ?)")) {
            stmt.setString(1, playerName);
            stmt.setInt(2, getWinsForPlayer(playerName) + 1);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private int getWinsForPlayer(String playerName) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT wins FROM players WHERE name = ?")) {
            stmt.setString(1, playerName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("wins");
            }
            return 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }




}

