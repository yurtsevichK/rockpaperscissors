package players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User extends Player {
    public User(String[] moves) {
        super(moves);
    }

    @Override
    public void doMove() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int selectedIndex;
        try {
            selectedIndex = Integer.parseInt(reader.readLine());
            if (selectedIndex < 0 || selectedIndex > moves.length) {
                throw new IOException();
            } else if (selectedIndex == 0) {
                return;
            }
            selectedMove = moves[selectedIndex - 1];
        } catch (IOException | NumberFormatException e) {
            System.out.println("Incorrect parameters. Please repeat.");
            doMove();
        }
    }
}
