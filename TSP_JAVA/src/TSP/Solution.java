package TSP;

import java.util.ArrayList;

public abstract class Solution {
    int startCityIdx;
    ArrayList<Integer> tourCityIdx;
    long constructionDuration;
    long optimizationDuration;
    double totalDistanceTravelled;
    long numberOfRoadsTravelled;

    Solution(){
        startCityIdx = -1;
        tourCityIdx = new ArrayList<Integer>();
        constructionDuration = 0;
        optimizationDuration = 0;
        totalDistanceTravelled = 0;
        numberOfRoadsTravelled = 0;
    }

    public ArrayList<Integer> getTourCityIdx() {
        return tourCityIdx;
    }

    public void setTourCityIdx(ArrayList<Integer> tourCityIdx) {
        this.tourCityIdx = tourCityIdx;
    }

    public long getConstructionDuration() {
        return constructionDuration;
    }

    public void setConstructionDuration(long constructionDuration) {
        this.constructionDuration = constructionDuration;
    }

    public long getOptimizationDuration() {
        return optimizationDuration;
    }

    public void setOptimizationDuration(long optimizationDuration) {
        this.optimizationDuration = optimizationDuration;
    }

    public double getTotalDistanceTravelled() {
        return totalDistanceTravelled;
    }

    public void setTotalDistanceTravelled(double totalDistanceTravelled) {
        this.totalDistanceTravelled = totalDistanceTravelled;
    }

    public long getNumberOfRoadsTravelled() {
        return numberOfRoadsTravelled;
    }

    public void setNumberOfRoadsTravelled(long numberOfRoadsTravelled) {
        this.numberOfRoadsTravelled = numberOfRoadsTravelled;
    }

    abstract void construct();

    abstract void improve();

    void printConstruction(){
        System.out.println("Start City = " + startCityIdx);
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

    public boolean checkSolution(){
        if(tourCityIdx.isEmpty()){
            System.out.println("Empty");
            return false;
        }

        int size = tourCityIdx.size();
        boolean[] visit = new boolean[Main.n];

        for (int i = 0; i < Main.n; i++) {
            visit[i] = false;
        }

        for (int i = 0; i < size-1; i++) {
            int idx = tourCityIdx.get(i);
            if(visit[idx]){
                System.out.println("Visited " + idx + " twice...");
                return false;
            }
            visit[idx] = true;
        }

        int start = tourCityIdx.get(0);
        int end = tourCityIdx.get(size-1);
        if( start != end){
            System.out.println("Start and End not the same...");
            return false;
        }

        for (int i = 0; i < Main.n; i++) {
            if (!visit[i]) {
                System.out.println("Didn't Visit " + i + "...");
                return false;
            }
        }

        System.out.println("CORRECT SOLUTION!!!");
        return true;
    }


}
