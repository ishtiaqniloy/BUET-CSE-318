package TSP;

public class K_Opt extends Solution {

    public K_Opt(Solution solution) {
        super();

        tourCityIdx.addAll(solution.tourCityIdx);
        constructionDuration = solution.getConstructionDuration();
        totalDistanceTravelled = solution.getTotalDistanceTravelled();
        numberOfRoadsTravelled = solution.getNumberOfRoadsTravelled();



    }

    @Override
    void construct() {
        //doing nothing here
    }

    @Override
    void improve() {

    }
}
