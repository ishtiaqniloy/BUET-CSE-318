package Mancala;

import java.util.ArrayList;

public class State {
    int turn;
    int [][]board;
    ArrayList<State> successors;

    State parent;

    public State(int turn, State p) {
        this.turn = turn;

        this.board = new int[7][7];
        this.successors = new ArrayList<State>();

        this.parent = p;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    int[][] getBoard() {
        int [][]newBoard = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                newBoard[i][j] = this.board[i][j];
            }
        }
        return newBoard;
    }

    void setBoard(int[][] newBoard) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                this.board[i][j] = newBoard[i][j];
            }
        }
    }

    public ArrayList<State> getSuccessors() {
        return new ArrayList<State>(successors);
    }

    public void setSuccessors(ArrayList<State> successors) {
        this.successors = new ArrayList<State>(successors);
    }

    boolean isTerminal(){
        for (int i = 0; i < 6; i++) {
            if(board[0][i] + board[1][i] > 0){
                return false;
            }
        }
        return true;
    }

    void printBoard(){
        System.out.println("\nPrinting Mancala board:");

        ///printing upper line
        for (int i = 5; i >=0 ; i--) {
            System.out.print("\t" + board[1][i]);
        }
        System.out.println();

        //printing two storage
        System.out.println(board[1][6] +"\t\t\t\t\t\t\t" + board[0][6]);

        ///printing lower line
        for (int i = 0; i < 6 ; i++) {
            System.out.print("\t" + board[0][i]);
        }
        System.out.println();


    }

    public int getStorageDiff(int playerNumber){
        return (board[playerNumber][6] - board[playerNumber^1][6]);
    }

    public int getBinsDiff(int playerNumber){
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += board[playerNumber][i] - board[playerNumber^1][i];
        }

        return sum;
    }

    public int extraTurn(int playerNumber){
        if(parent==null){
            return 0;
        }
        else if(parent.getTurn()==playerNumber && this.getTurn()==playerNumber){
            return 1;
        }
        else if(parent.getTurn()==this.getTurn()){
            return -1;
        }
        else {
            return 0;
        }
    }

    public int numStoneCaptured(int playerNumber){
        if(parent==null){
            return this.board[playerNumber][6];
        }
        else{
            return (this.board[playerNumber][6] - parent.board[playerNumber][6]);
        }
    }


}
