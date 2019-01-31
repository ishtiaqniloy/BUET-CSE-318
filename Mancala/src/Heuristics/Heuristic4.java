package Heuristics;

import Mancala.State;

import java.util.Scanner;

public class Heuristic4 implements Heuristic {
    private int w1;
    private int w2;
    private int w3;
    private int w4;

    public Heuristic4() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter values of w1, w2, w3 and w4: ");

        w1 = scanner.nextInt();
        w2 = scanner.nextInt();
        w3 = scanner.nextInt();
        w4 = scanner.nextInt();

        System.out.println(w1 + " " + w2 + " " + w3 + " " + w4);

    }

    @Override
    public int evaluateState(State state, int playerNumber) {
        return 0;
    }
}
