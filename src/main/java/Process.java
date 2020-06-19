import players.Computer;
import players.User;
import hmac.Hmac;
import utils.StringParametersUtility;
import utils.Utility;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class Process {

    private final String[] moves;
    private final Utility<String> utility;
    private final Hmac hmac;
    private final Computer computer;
    private final User user;

    public Process(String[] moves) {
        this.moves = moves;
        utility = new StringParametersUtility();
        hmac = new Hmac();
        computer = new Computer(moves);
        user = new User(moves);
    }

    public void start() {
        try {
            utility.isValid(moves);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
            return;
        }
        computer.doMove();
        showHmacMove(computer.getSelectedMove());
        showAvailableMoves();
        user.doMove();
        int userIndexMove = getIndexMove(user.getSelectedMove());
        showUserMove(userIndexMove);
        if (userIndexMove < 0) {
            return;
        }
        int indexCpuMove = getIndexMove(computer.getSelectedMove());
        showCpuMove(indexCpuMove);
        showWinner(findWinner(indexCpuMove, userIndexMove));
        showHmacKey();
    }

    private void showHmacMove(String move) {
        System.out.printf("HMAC: %s\n", hmac.createHmac(move));
    }

    private int getIndexMove(String move) {
        return Arrays.asList(moves).indexOf(move);
    }

    private void showAvailableMoves() {
        System.out.println("Available moves:");
        for (int i = 1; i <= moves.length; i++) {
            System.out.printf("%d - %s\n", i, moves[i - 1]);
        }
        System.out.println("0 - Exit");
    }

    private void showUserMove(int move) {
        if (move < 0) {
            System.out.printf("You typed: %d\nGood bye!\n", move + 1);
        } else {
            System.out.printf("You typed: %d\nYou move: %s\n", move + 1, moves[move]);
        }
    }

    private void showCpuMove(int cpuStep) {
        System.out.printf("Computer move: %s\n", moves[cpuStep]);
    }

    private String findWinner(int cpuSelect, int userSelect) {
        if (cpuSelect == userSelect)
            return null;
        int counter = ++cpuSelect;
        for (int i = 0; i < moves.length / 2; i++) {
            if (counter == moves.length) {
                counter = 0;
            }
            if (counter == userSelect) {
                return "User";
            }
            counter++;
        }
        return "Computer";
    }

    private void showWinner(String winner) {
        if (winner == null || winner.isEmpty())
            System.out.println("The casino wins!");
        else
            System.out.println(winner + " wins!");
    }

    private void showHmacKey() {
        System.out.printf("HMAC key: %s\n", hmac.getKey());
    }

}
