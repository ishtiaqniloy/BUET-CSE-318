package TSP;

import java.util.ArrayList;

public abstract class Solution {
    ArrayList<Integer> tourCityIdx;
    long constructionDuration;
    long optimizationDuration;
    double totalDistanceTravelled;
    long numberOfRoadsTravelled;

    Solution(){
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

    public double getConstructionDuration() {
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
