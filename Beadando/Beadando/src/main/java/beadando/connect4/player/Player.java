package beadando.connect4.player;

public abstract class Player {
    public final String name;
    public final int playerNumber;

    protected Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
    }

    public abstract int makeMova();
}
