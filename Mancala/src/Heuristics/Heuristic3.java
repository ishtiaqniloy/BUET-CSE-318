package Heuristics;

import Mancala.State;

import java.util.Scanner;

public class Heuristic3 implements Heuristic {
    private int w1;
    private int w2;
    private int w3;

    public Heuristic3() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter values of w1, w2 and w3: ");

        w1 = scanner.nextInt();
        w2 = scanner.nextInt();
        w3 = scanner.nextInt();

        System.out.println(w1 + " " + w2 + " " + w3);

    }



    @Override
    public int evaluateState(State state, int playerNumber) {
        return 0;
    }
}
