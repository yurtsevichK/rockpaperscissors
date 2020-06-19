
public class Game {

    private final Process process;

    public Game(String[] moves) {
        this.process = new Process(moves);
    }

    public static void main(String[] args) {
        Game game = new Game(args);
        game.process.start();
    }
}
