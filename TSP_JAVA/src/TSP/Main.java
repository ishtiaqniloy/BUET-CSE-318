package TSP;

import javafx.util.Pair;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final boolean RANDOMIZED_START = true;
    public static final int MAX_ITR = 500;
    public static final int MAX_CANDIDATES = 5;
    public static final Random rand = new Random();

    public static int constructionWrongSolution = 0;
    public static int optimizationWrongSolution = 0;
    public static int n;
    public static ArrayList<Pair<Float, Float>> locations;

    public static int k = 2;

    public static void main(String []args){
        System.out.println("Travelling Salesman Problem");

        int constructionChoice, optimizationChoice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Construction Heuristic: 1.Nearest Neighbor\t2.Savings");
        constructionChoice = sc.nextInt();

        System.out.println("Optimization Heuristic: 0.None\t1.k-Opt");
        optimizationChoice = sc.nextInt();

//        if(optimizationChoice==1){
//            System.out.println("Enter value of k");
//            k = sc.nextInt();
//        }

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

            ArrayList<Solution> solutions = new ArrayList<Solution>();

            double minDist = 999999999;
            int minIdx = -1;
            double avgDist = 0;
            double avgDuration = 0;

            if(constructionChoice == 1 ){
                //=============================================================
                //Nearest Neighbor Heuristic Construction
                //=============================================================
                System.out.println("Starting Nearest Neighbor Heuristic Construction...");
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
                System.out.println("Starting Savings Heuristic Construction...");
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

                avgDist += s.getTotalDistanceTravelled() / MAX_ITR;
                avgDuration += s.getConstructionDuration()*1.0 / MAX_ITR;

                if(s.getTotalDistanceTravelled() < minDist){
                    minDist = s.getTotalDistanceTravelled();
                    minIdx = i;
                }

                System.out.println("Printing " + (i+1) +"th solution...");
                s.printConstruction();

            }

            if(constructionChoice == 1){
                System.out.println("***FINAL RESULTS OF NEAREST NEIGHBOR HEURISTIC****");
                System.out.println("Average Time required for construction in Nearest Neighbor Heuristic = " + avgDuration + "ms");
                System.out.println("Average Distance Travelled in Nearest Neighbor Heuristic = " + avgDist);
                System.out.println();

                System.out.println("Minimum Distance Solution Found in Nearest Neighbor Heuristic :");
                solutions.get(minIdx).printConstruction();
            }
            else if(constructionChoice == 2){
                System.out.println("***FINAL RESULTS OF SAVINGS HEURISTIC****");
                System.out.println("Average Time required for construction in Savings Heuristic = " + avgDuration + "ms");
                System.out.println("Average Distance Travelled in Savings Heuristic = " + avgDist);
                System.out.println();

                System.out.println("Minimum Distance Solution Found in Savings Heuristic :");
                solutions.get(minIdx).printConstruction();
            }

            System.out.println("Number of Wrong Solutions = " + constructionWrongSolution);


            if(optimizationChoice == 1){

            }
            else if(optimizationChoice>1){
                System.out.println("Wrong choice of Optimization Heuristic");
            }



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
