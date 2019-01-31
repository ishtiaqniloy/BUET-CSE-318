package Heuristics;

import Mancala.State;

import java.util.Scanner;

public class Heuristic2 implements Heuristic {
    private int w1;
    private int w2;

    public Heuristic2() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter values of w1 and w2: ");

        w1 = scanner.nextInt();
        w2 = scanner.nextInt();

        System.out.println(w1 + " " + w2);

    }

    @Override
    public int evaluateState(State state, int playerNumber) {

        return w1*state.getStorageDiff(playerNumber)+w2*state.getBinsDiff(playerNumber);
    }
}
