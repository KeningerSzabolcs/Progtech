package beadando.connect4.game;

import beadando.connect4.board.Board;
import beadando.connect4.player.ComputerPlayer;
import beadando.connect4.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Board realBoard;
    private HumanPlayer realHumanPlayer;
    private ComputerPlayer realComputerPlayer;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        realBoard = new Board(9,9);
        realHumanPlayer = new HumanPlayer("Player1",1);
        realComputerPlayer = new ComputerPlayer("computer",2);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(originalOut);
    }

    @Test
    void testGameInitialization() {
        Game game = new Game();
        assertNotNull(game);
    }

    @Test
    void testGameInitializationWithRealInstances() {
        Game game = new Game(realBoard, realHumanPlayer, realComputerPlayer);
        assertNotNull(game);
    }

    @Test
    void testLoadFromFileWithException() {
        Game game = new Game();
        game.loadFromFile("invalid_file.txt");
        assertTrue(outContent.toString().contains("Nem található a fájl"));
    }

    @Test
    void testInitializeDatabase() throws SQLException {
        Game game = new Game();
        assertDoesNotThrow(game::initializeDatabase);
    }

    @Test
    void testShowTopWinners() {
        Game game = new Game();
        game.showTopWinners(3);
        assertTrue(outContent.toString().contains("Top 3 Winners:"));
    }

    @Test
    void testUpdatePlayerWins() throws SQLException {
        Game game = new Game(realBoard, realHumanPlayer, realComputerPlayer);
        game.updatePlayerWins("TestPlayer");
    }

    @Test
    void testGetWinsForPlayer() {
        Game game = new Game();
        assertEquals(0, game.getWinsForPlayer("NonExistingPlayer"));
    }





    @Test
    void testGameWithContinuousRandomMoves() {
        Game game = new Game(realBoard, realHumanPlayer, realComputerPlayer);

        Random random = new Random();

        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append("Player1\n");
        inputBuilder.append("n\n");

        InputStream in = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        System.setIn(in);
        Thread gameThread = new Thread(() -> {
            game.start();
        });
        gameThread.start();


        boolean gameRunning = true;


        while (gameRunning) {

            int randomMove = random.nextInt(9);
            inputBuilder.append(randomMove).append("\n");

            in = new ByteArrayInputStream(inputBuilder.toString().getBytes());
            System.setIn(in);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gameRunning = false;
        }
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertDoesNotThrow(() -> gameThread.join());
    }

    @Test
    void testLoadFromFileUsingReflection() throws IOException, NoSuchFieldException, IllegalAccessException {
        File tempFile = File.createTempFile("test_board", ".txt");
        tempFile.deleteOnExit();

        String testData =
                "1 2 3 4 5 6 7 8 9\n" +
                        "4 5 6 7 8 9 1 2 3\n" +
                        "7 8 9 1 2 3 4 5 6\n" +
                        "2 3 4 5 6 7 8 9 1\n" +
                        "5 6 7 8 9 1 2 3 4\n" +
                        "8 9 1 2 3 4 5 6 7\n" +
                        "3 4 5 6 7 8 9 1 2\n" +
                        "6 7 8 9 1 2 3 4 5\n" +
                        "9 1 2 3 4 5 6 7 8\n";

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(testData);
        }

        Game game = new Game();
        game.loadFromFile(tempFile.getAbsolutePath());

        Field boardField = Game.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Object boardObject = boardField.get(game);

        Field gridField = boardObject.getClass().getDeclaredField("grid");
        gridField.setAccessible(true);
        int[][] actualGrid = (int[][]) gridField.get(boardObject);

        int[][] expectedGrid = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };

        assertNotNull(actualGrid, "The grid should not be null.");
        assertEquals(9, actualGrid.length, "The grid should have 9 rows.");
        for (int i = 0; i < 9; i++) {
            assertArrayEquals(expectedGrid[i], actualGrid[i], "Row " + i + " should match.");
        }
    }
    @Test
    void testSaveToFile() throws IOException, NoSuchFieldException, IllegalAccessException {
        File tempFile = File.createTempFile("test_save_board", ".txt");
        tempFile.deleteOnExit();

        Game game = new Game();
        int[][] testData = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };

        Field boardField = Game.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Object boardObject = boardField.get(game);

        Field gridField = boardObject.getClass().getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(boardObject, testData);

        game.saveToFile(tempFile.getAbsolutePath());

        StringBuilder fileContents = new StringBuilder();
        try (Scanner scanner = new Scanner(tempFile)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append("\n");
            }
        }

        String expectedContents =
                "1 2 3 4 5 6 7 8 9 \n" +
                        "4 5 6 7 8 9 1 2 3 \n" +
                        "7 8 9 1 2 3 4 5 6 \n" +
                        "2 3 4 5 6 7 8 9 1 \n" +
                        "5 6 7 8 9 1 2 3 4 \n" +
                        "8 9 1 2 3 4 5 6 7 \n" +
                        "3 4 5 6 7 8 9 1 2 \n" +
                        "6 7 8 9 1 2 3 4 5 \n" +
                        "9 1 2 3 4 5 6 7 8 \n";

        assertEquals(expectedContents.trim(), fileContents.toString().trim(), "File contents should match the board.");
    }

    @Test
    void testSaveToFileWithExceptionHandling() throws NoSuchFieldException, IllegalAccessException {
        String invalidFilePath = "/invalid/path/test_save_board.txt";
        Game game = new Game();
        int[][] testData = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };

        Field boardField = Game.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Object boardObject = boardField.get(game);

        Field gridField = boardObject.getClass().getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(boardObject, testData);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        game.saveToFile(invalidFilePath);

        String output = outputStream.toString();
        assertTrue(output.contains("Nem lehet létrehozni a fájlt"), "Error message should be printed to System.out.");

        System.setOut(System.out);
    }






}








