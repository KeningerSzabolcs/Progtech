package beadando.connect4.game;

import beadando.connect4.board.Board;
import beadando.connect4.player.ComputerPlayer;
import beadando.connect4.player.HumanPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




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
        HumanPlayer player = new HumanPlayer(playerName, 1);
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
                    System.out.println(player.getName() + " wins!");
                    updatePlayerWins(player.getName());
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

    /**
     * Initializes the database by creating the necessary table
     * for storing player data.
     * If the table does not already exist, it will be created
     * with the specified schema.
     * <p>
     * The table, named `players`,
     * contains the following columns:
     * <ul>
     *   <li><strong>name</strong>
     *   - The name of the player (unique, primary key).</li>
     *   <li><strong>wins</strong>
     *   - The number of games the player has won (integer).</li>
     * </ul>
     * <p>
     * This method uses a JDBC connection to interact
     * with the database. If the connection
     * fails or an SQL error occurs, an error message
     * is printed to the console.
     *
     * @throws SQLException if a database
     * access error occurs or the SQL statement is invalid.
     */

    public void initializeDatabase() {
        try (Connection conn =
                     DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS players"
                    + " (name VARCHAR(255) PRIMARY KEY, wins INT)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Displays the top players with the highest number
     * of wins from the database.
     * The results are ordered by the number of wins
     * in descending order, showing
     * the top `numWinners` players.
     *
     * @param numWinners the maximum
     *                  number of top players to display.
     *                   If there are fewer players in the database,
     *                   it will display all of them.
     * <p>
     * The method connects to the database,
     *                  executes a query to retrieve player names
     * and their corresponding win counts,
     *                  and prints them to the console in the format:
     * <code>PlayerName - X wins</code>.
     * </p>
     * <p>
     * Example output for `numWinners = 3`:
     * <pre>
     * Top 3 Winners:
     * Szabi - 10 wins
     * Boti - 8 wins
     * Akos - 7 wins
     * </pre>
     * </p>
     * @throws SQLException if a database access
     * error occurs or the query execution fails.
     */

    public void showTopWinners(final int numWinners) {
        try (Connection conn =
                     DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt =
                     conn.prepareStatement("SELECT name, wins FROM"
                             + " players ORDER BY wins DESC")) {
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

    /**
     * Updates the win count for a specific player in the database.
     * If the player does not already exist in the database,
     * a new entry is created with an initial win count of 1.
     * If the player already exists, their win count is incremented by 1.
     *
     * @param playerName the name of the player whose
     *                  win count is to be updated.
     *                   This is used as the primary key in the database.
     * <p>
     * The method uses an SQL `INSERT OR REPLACE`
     *                  statement to either insert a new record
     * or update an existing one. The win count
     *                  is retrieved using
     *                  the {@link #getWinsForPlayer(String)} method
     * and incremented by 1 before being updated in the database.
     * </p>
     * @throws SQLException if a database access
     * error occurs or the query execution fails.
     */

    private void updatePlayerWins(final String playerName) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt =
                     conn.prepareStatement("INSERT OR REPLACE "
                             + "INTO players (name, wins) VALUES (?, ?)")) {
            stmt.setString(1, playerName);
            stmt.setInt(2,
                    getWinsForPlayer(playerName) + 1);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    private int getWinsForPlayer(final String playerName) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt =
                conn.prepareStatement("SELECT wins "
                        + "FROM players WHERE name = ?")) {
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

