package TSP;

import javafx.util.Pair;

import java.util.ArrayList;

public class NearestNeighborSolution2 extends Solution {
    int startCityIdx;
    int numberOfCitiesVisited;

    long startTime;

    boolean []visited;

    NearestNeighborSolution2(){
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

    ArrayList<Pair<Integer, Float>> calculateCandidateNeighbors(int currentCityIdx){ //O(n*MAX_CANDIDATES) sort
        ArrayList<Pair<Integer, Float>> candidates = new ArrayList<Pair<Integer, Float>>();

        //Calculating all cities distance from current city
        float [] distanceArray = new float[Main.n];
        float x1 = Main.locations.get(currentCityIdx).getKey();
        float y1 = Main.locations.get(currentCityIdx).getValue();
        for (int j = 0; j < Main.n; j++) {
            float x2 = Main.locations.get(j).getKey();
            float y2 = Main.locations.get(j).getValue();
            distanceArray[j] = (float) Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
        }

        double prevMin = -1;
        for (int i = 0; i < Main.MAX_CANDIDATES; i++) {
            float minVal = 999999999;
            int minIdx = -1;

            for (int j = 0; j < Main.n; j++) {
                if( j==currentCityIdx || visited[j]){
                    continue;
                }

                float val = distanceArray[j];
                if(val < minVal && val >= prevMin && !candidates.contains(j)){
                    minVal = val;
                    minIdx = j;
                }

            }

            if(minIdx==-1){ //no suitable candidates found
                break;
            }

            candidates.add(new Pair<>(minIdx, minVal) );
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
            ArrayList<Pair<Integer, Float>> candidateCities = calculateCandidateNeighbors(currentCityIdx);

            int nextCityIdx = candidateCities.get( Main.rand.nextInt(candidateCities.size())).getKey();
            float nextCityDist = candidateCities.get( Main.rand.nextInt(candidateCities.size())).getValue();

            tourCityIdx.add(nextCityIdx);
            visited[nextCityIdx] = true;
            totalDistanceTravelled += nextCityDist;
            numberOfRoadsTravelled++;
            numberOfCitiesVisited++;

            currentCityIdx = nextCityIdx;
        }

        //going back to start city
        tourCityIdx.add(startCityIdx);
        float x1 = Main.locations.get(currentCityIdx).getKey();
        float x2 = Main.locations.get(currentCityIdx).getValue();
        float y1 = Main.locations.get(startCityIdx).getKey();
        float y2 = Main.locations.get(startCityIdx).getValue();
        totalDistanceTravelled +=  (float) Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
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
