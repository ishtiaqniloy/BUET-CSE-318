package TSP;

import javafx.util.Pair;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int SIMPLE_ITR = 5;
    public static final int RANDOM_ITR = 10;
    public static final int MAX_CANDIDATES = 5;
    public static final int MAX_OPT = 3;
    public static final Random rand = new Random();


    public static int MAX_ITR = SIMPLE_ITR;
    public static boolean RANDOMIZED_START = true;
    public static int USE_CANDIDATES = 1;

    public static int savingsR=0;
    public static int constructionWrongSolution = 0;
    public static int optimizationWrongSolution = 0;

    public static int n;
    public static int startCity;
    public static ArrayList<Pair<Float, Float>> locations;

    public static int k = 2;

    public static void main(String []args){
        System.out.println("Travelling Salesman Problem");

        int constructionChoice, optimizationChoice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Construction Heuristic: 1.Nearest Neighbor\t2.Savings");
        constructionChoice = sc.nextInt();

        sc.close();

        try{
            File input = new File("Input.txt");
            if(input.exists()){
                System.out.println("Input file found!");
            }
            else{
                System.out.println("Input file not found. Exiting...");
                return;
            }

            Scanner scanner = new Scanner(new FileInputStream(input));

            //=============================================================
            //Input
            //=============================================================

            System.out.println("Taking Inputs...");

//            while(scanner.hasNextLine()){
//                System.out.println(scanner.nextLine());
//            }

            n = scanner.nextInt();
            System.out.println(n + " cities in the problem.\nLocations are:");

            locations = new ArrayList<Pair<Float, Float>>();

            int idx;
            float x, y;
            for (int i = 0; i < n; i++) {
                idx = scanner.nextInt();    //for complying with test case format
                x = scanner.nextFloat();
                y = scanner.nextFloat();

                locations.add(new Pair<>(x, y));
                System.out.println(locations.get(i).getKey() + " " + locations.get(i).getValue());

            }

            scanner.close();


            //=============================================================
            //CHOOSING START CITY
            //=============================================================
            ArrayList<Solution> solutions = new ArrayList<Solution>();

            double avgSimpleDist = 0;
            double avgSimpleDuration = 0;
            double minSimpleDist = 999999999;
            int minSimpleIdx = -1;
            Solution minSimpleSolution;
            double maxSimpleDist = -1;
            int maxSimpleIdx = -1;
            Solution maxSimpleSolution;


            if(constructionChoice == 1 ){
                //=============================================================
                //Nearest Neighbor Heuristic Construction
                //=============================================================
                System.out.println("Starting Simple Nearest Neighbor Heuristic Construction...");
                System.out.println();

                for (int i = 0; i < MAX_ITR; i++) {
                    System.out.println("Running " + (i+1) + "th iteration...");
                    Solution s = new NearestNeighbor();
                    solutions.add(s);
                    s.construct();
                }

            }
            else if(constructionChoice == 2){
                //=============================================================
                //Savings Heuristic Construction
                //=============================================================
                System.out.println("Starting Simple Savings Heuristic Construction...");
                System.out.println();

                for (int i = 0; i < MAX_ITR; i++) {
                    System.out.println("Running " + (i+1) + "th iteration...");
                    Solution s = new Savings();
                    solutions.add(s);
                    s.construct();
                }

            }
            else{
                System.out.println("Wrong choice of Construction Heuristic");
            }

            for (int i = 0; i < MAX_ITR; i++) {
                Solution s = solutions.get(i);

                avgSimpleDist += s.getTotalDistanceTravelled() / MAX_ITR;
                avgSimpleDuration += s.getConstructionDuration()*1.0 / MAX_ITR;

                if(s.getTotalDistanceTravelled() < minSimpleDist){
                    minSimpleDist = s.getTotalDistanceTravelled();
                    minSimpleIdx = i;
                }

                if(s.getTotalDistanceTravelled() > maxSimpleDist){
                    maxSimpleDist = s.getTotalDistanceTravelled();
                    maxSimpleIdx = i;
                }

                System.out.println("Printing " + (i+1) +"th solution of Greedy Simple Version...");
                s.printConstruction();

            }

            minSimpleSolution = solutions.get(minSimpleIdx);
            maxSimpleSolution = solutions.get(minSimpleIdx);

            //=============================================================
            //FINDING BEST TOUR FROM START CITY
            //=============================================================

            //Initializing variables for constructions from startCity
            startCity = minSimpleSolution.getStartCityIdx();
            RANDOMIZED_START = false;
            USE_CANDIDATES = MAX_CANDIDATES;
            MAX_ITR = RANDOM_ITR;
            solutions.clear();


            double avgRandomDist = 0;
            double avgRandomDuration = 0;
            double minRandomDist = 999999999;
            int minRandomIdx = -1;
            Solution minRandomSolution;
            double maxRandomDist = -1;
            int maxRandomIdx = -1;
            Solution maxRandomSolution;


            //=============================================================
            //SHOWING RESULTS
            //=============================================================


            //GREEDY SIMPLE RESULT
            System.out.println("***FINAL RESULTS OF GREEDY SIMPLE VERSION****");
            System.out.println("Average Time required for construction in Greedy Simple Version = " + avgSimpleDuration + "ms");
            System.out.println("Average Distance Travelled in Greedy Simple Version = " + avgSimpleDist);
            System.out.println();

            System.out.println("Best Solution in Greedy Simple Version:");
            minSimpleSolution.printConstruction();

            System.out.println("Best Solution in Greedy Simple Version:");
            maxSimpleSolution.printConstruction();


            //GREEDY SIMPLE RESULT
            System.out.println("***FINAL RESULTS OF GREEDY RANDOMIZED VERSION****");
            System.out.println("Average Time required for construction in Greedy Randomized Version = " + avgRandomDuration + "ms");
            System.out.println("Average Distance Travelled in Greedy Randomized Version = " + avgRandomDist);
            System.out.println();

            System.out.println("Best Solution in Greedy Randomized Version:");
            minRandomSolution.printConstruction();

            System.out.println("Best Solution in Greedy Randomized Version:");
            maxRandomSolution.printConstruction();

            System.out.println();
            System.out.println();



            System.out.println("savingsR = " + savingsR);

            System.out.println("Number of Construction Wrong Solutions = " + constructionWrongSolution);


        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public static float calculateDistance(int i, int j){
        float x1 = locations.get(i).getKey();
        float y1 = locations.get(i).getValue();

        float x2 = locations.get(j).getKey();
        float y2 = locations.get(j).getValue();

        return (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

}
