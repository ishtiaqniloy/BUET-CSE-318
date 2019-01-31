package Mancala;

import Heuristics.Heuristic;
import javafx.util.Pair;

import java.util.ArrayList;

public class Player{
    int playerNumber;
    Heuristic heuristic;

    public Player(int playerNumber, Heuristic heuristic) {
        this.playerNumber = playerNumber;
        this.heuristic = heuristic;


    }

    State giveMove(State currentState){
        State tempState = minimax(currentState, Main.MINIMAX_SEARCH_DEPTH, true).getKey();
//        tempState.getParent().printBoard();

        while (tempState!=null && tempState.getParent()!=null && tempState.getParent()!=currentState ){
            tempState = tempState.getParent();
        }

        if(tempState==null){
            System.out.println("NULL OCCURRED"); ///???????????????????????????????????????????
        }

        return tempState;
    }

    @SuppressWarnings("Duplicates")
    private Pair<State, Integer> minimax(State state, int depth, boolean maximizingPlayer){

//        if(state.isTerminal()){
//            System.out.println("Terminal State found:");    ///?
//            state.printBoard();
//        }

        if(depth == 0 || state.isTerminal()){
            return new Pair<State, Integer>(state,heuristic.evaluateState(state, playerNumber));
        }
        else if(maximizingPlayer){
            Pair<State, Integer> maxPair = new Pair<State, Integer>(null, -999999); ///?
            ArrayList<State> children = state.generateSuccessors();
            for (State child:children){
                Pair<State, Integer> tempPair = minimax(child, depth-1, child.getTurn()==playerNumber);
                if(tempPair.getValue() > maxPair.getValue()){
                    maxPair = tempPair;
                }
            }

            if(maxPair.getKey()==null){
                System.out.println("Returning null from minimax elseif at depth = " + depth); ///?
                state.printBoard();
            }

            return maxPair;
        }
        else{
            Pair<State, Integer> minPair = new Pair<State, Integer>(null, 999999); ///?
            ArrayList<State> children = state.generateSuccessors();
            for (State child:children){
                Pair<State, Integer> tempPair = minimax(child, depth-1, child.getTurn()==playerNumber);
                if(tempPair.getValue() < minPair.getValue()){
                    minPair = tempPair;
                }
            }

            if(minPair.getKey()==null){
                System.out.println("Returning null from minimax else at depth = " + depth); ///?
                state.printBoard();
            }

            return minPair;
        }
    }


}
