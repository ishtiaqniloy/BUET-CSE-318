package TSP;

import java.util.ArrayList;
import java.util.Random;

public class NearestNeighborSolution extends Solution {
    int startIdx;
    int numberOfVisitedCities;

    long startTime;

    boolean []visited;

    NearestNeighborSolution(){
        super();
        startTime = System.currentTimeMillis();

        startIdx =  Main.rand.nextInt() % Main.n;
        numberOfVisitedCities = 1;  //start city is visited

        visited = new boolean[Main.n];
        for (int i = 0; i < Main.n; i++) {
            visited[i] = false;
        }
        visited[startIdx] = true;

    }


    @Override
    void construct() {
        int currentIdx = startIdx;

        while(numberOfVisitedCities!=Main.n){
            ArrayList<Integer> candidateCitiesIdx  new ArrayList<Integer>();

            double prevMax = 999999999;
            double precIdx = -1;
            for (int i = 0; i < Main.MAX_CANDIDATES; i++) {

            }

            int nextCityIdx;


        }

    }

    @Override
    void improve() {
        //doing nothing here
    }
}
