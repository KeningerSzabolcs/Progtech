package beadando.connect4;

import beadando.connect4.game.Game;

public final class Main {
    private  Main() {
    }

    public static void main(final String[] args) {
        Game game = new Game();
        game.start();

    }
}
