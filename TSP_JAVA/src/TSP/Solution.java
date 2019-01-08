package TSP;

import java.util.ArrayList;

public abstract class Solution {
    ArrayList<Integer> tourCityIdx;
    double duration;
    double totalDistanceTravelled;
    int numberOfRoadsTravelled;

    Solution(){
        tourCityIdx = new ArrayList<Integer>();
        duration = 0;
        totalDistanceTravelled = 0;
        numberOfRoadsTravelled = 0;
    }

    public ArrayList<Integer> getTourCityIdx() {
        return tourCityIdx;
    }

    public void setTourCityIdx(ArrayList<Integer> tourCityIdx) {
        this.tourCityIdx = tourCityIdx;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getTotalDistanceTravelled() {
        return totalDistanceTravelled;
    }

    public void setTotalDistanceTravelled(double totalDistanceTravelled) {
        this.totalDistanceTravelled = totalDistanceTravelled;
    }

    public int getNumberOfRoadsTravelled() {
        return numberOfRoadsTravelled;
    }

    public void setNumberOfRoadsTravelled(int numberOfRoadsTravelled) {
        this.numberOfRoadsTravelled = numberOfRoadsTravelled;
    }

    abstract void construct();

    abstract void improve();

}
