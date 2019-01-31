package Mancala;

import Heuristics.Heuristic;

public class Player{
    int playerNumber;
    Heuristic heuristic;

    public Player(int playerNumber, Heuristic heuristic) {
        this.playerNumber = playerNumber;
        this.heuristic = heuristic;


    }

    State giveMove(){
        State state = new State(0, null);



        return state;
    }

}
