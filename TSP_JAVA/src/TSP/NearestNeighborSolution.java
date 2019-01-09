package TSP;

import java.util.ArrayList;

public class NearestNeighborSolution extends Solution {
    int startCityIdx;
    int numberOfCitiesVisited;

    long startTime;

    boolean []visited;

    NearestNeighborSolution(){
        super();

        if (Main.RANDOMIZED_START){
            startCityIdx =  Main.rand.nextInt(Main.n);
        }
        else{
            startCityIdx = 0;
        }

        tourCityIdx.add(startCityIdx);
        System.out.println("Starting City: " + startCityIdx);

        numberOfCitiesVisited = 1;  //start city is visited

        visited = new boolean[Main.n];
        for (int i = 0; i < Main.n; i++) {
            visited[i] = false;
        }
        visited[startCityIdx] = true;

    }

    ArrayList<Integer> calculateCandidateNeighbors(int currentCityIdx){ //O(n*MAX_CANDIDATES) sort
        ArrayList<Integer> candidates = new ArrayList<Integer>();

        double prevMin = -1;
        for (int i = 0; i < Main.MAX_CANDIDATES; i++) {
            double minVal = 999999999;
            int minIdx = -1;

            for (int j = 0; j < Main.n; j++) {
                if( j==currentCityIdx || visited[j]){
                    continue;
                }

                double val = Main.distanceArray[currentCityIdx][j];
                if(val < minVal && val >= prevMin && !candidates.contains(j)){
                    minVal = val;
                    minIdx = j;
                }

            }

            if(minIdx==-1){ //no suitable candidates found
                break;
            }

            candidates.add(minIdx);
            prevMin = minVal;

        }

        return candidates;
    }

    @Override
    void construct() {
        System.out.println("Starting Construction in Nearest Neighbor Heuristic");
        startTime = System.currentTimeMillis();

        int currentCityIdx = startCityIdx;

        while(numberOfCitiesVisited!=Main.n){
            ArrayList<Integer> candidateCities = calculateCandidateNeighbors(currentCityIdx);

            int nextCityIdx = candidateCities.get( Main.rand.nextInt(candidateCities.size())) ;

            tourCityIdx.add(nextCityIdx);
            visited[nextCityIdx] = true;
            totalDistanceTravelled += Main.distanceArray[currentCityIdx][nextCityIdx];
            numberOfRoadsTravelled++;
            numberOfCitiesVisited++;

            currentCityIdx = nextCityIdx;
        }

        //going back to start city
        tourCityIdx.add(startCityIdx);
        totalDistanceTravelled += Main.distanceArray[currentCityIdx][startCityIdx];
        numberOfRoadsTravelled++;

        constructionDuration = System.currentTimeMillis() - startTime;

        System.out.println("Finished Construction in Nearest Neighbor Heuristic");
        System.out.println();
    }

    @Override
    void improve() {
        //doing nothing here
    }

    @Override
    void printSolution() {
        System.out.println("Total Distance = " + totalDistanceTravelled);
        System.out.println("Number of roads travelled = " + numberOfRoadsTravelled);
        System.out.println("Time Required For construction = " + constructionDuration +"ms");
        System.out.print("Printing city indices: ");
        for (int i = 0; i < tourCityIdx.size(); i++) {
            System.out.print(tourCityIdx.get(i) + " ");
        }
        System.out.println();
        System.out.println();

    }

}
