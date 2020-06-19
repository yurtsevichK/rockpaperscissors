package players;

import java.security.SecureRandom;

public class Computer extends Player{

    public Computer(String[] moves) {
        super(moves);
    }

    @Override
    public void doMove() {
        SecureRandom random = new SecureRandom();
        selectedMove = moves[random.nextInt(moves.length)];
    }
}
