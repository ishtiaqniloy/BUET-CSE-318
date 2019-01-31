/**
 * Two players are Player0 and Player1
 *
 **/

package Mancala;

import Heuristics.*;

import java.util.Scanner;

public class Main {
    public static final int MINIMAX_SEARCH_DEPTH = 5;


    public static void main(String []args){
        System.out.println("******Welcome to Mancala******");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Give Player0 heuristic (1~4): ");
        int choice0 = scanner.nextInt();
        Heuristic player0Heuristic = getHeuristic(choice0);
        Player player0 = new Player(0, new Heuristic1());



        System.out.print("Give Player1 heuristic (1~4): ");
        int choice1 = scanner.nextInt();
        Heuristic player1Heuristic = getHeuristic(choice1);
        Player player1 = new Player(1, new Heuristic1());

        State currentState = getInitialState();

        currentState.printBoard();

        while (!currentState.isTerminal()){
            if(currentState.getTurn()==0){
                currentState = player0.giveMove();
            }
            else {
                currentState = player1.giveMove();
            }
        }



    }


    private static Heuristic getHeuristic(int choice){
        switch (choice){
            case 1: return new Heuristic1();
            case 2: return new Heuristic2();
            case 3: return new Heuristic3();
            case 4: return new Heuristic4();
            default:
                System.out.println("Wrong choice of heuristic. Default heuristic set...");
                return new Heuristic1();


        }


    }

    private static State getInitialState(){
        State state = new State(0, null);
        ///Initialize
        int [][]newBoard = new int[7][7];

        for (int i = 0; i < 6; i++) {
            newBoard[0][i] = 4;
            newBoard[1][i] = 4;
        }
        newBoard[0][6] = 0;
        newBoard[1][6] = 0;

        state.setBoard(newBoard);

        return state;
    }


}
