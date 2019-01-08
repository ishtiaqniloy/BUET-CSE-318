package TSP;

import javafx.util.Pair;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final boolean RANDOMIZED_START = true;
    public static final int MAX_ITR = 10;
    public static final int MAX_CANDIDATES = 3;
    public static final Random rand = new Random();

    public static int n;
    public static double [][] distanceArray;


    public static void main(String []args){
        System.out.println("Travelling Salesman Problem");

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
            //Input Processing
            //=============================================================

            System.out.println("Taking Inputs...");

//            while(scanner.hasNextLine()){
//                System.out.println(scanner.nextLine());
//            }

            n = scanner.nextInt();
            System.out.println(n + " cities in the problem.\nLocations are:");

            ArrayList<Pair<Integer, Integer>> locations = new ArrayList<Pair<Integer, Integer>>();

            int x, y;
            for (int i = 0; i < n; i++) {
                x = scanner.nextInt();
                y = scanner.nextInt();

                locations.add(new Pair<>(x, y));
                System.out.println(locations.get(i).getKey() + " " + locations.get(i).getValue());

            }

            scanner.close();

            System.out.println("Calculating Distances...");

            distanceArray = new double[n][n];
            for (int i = 0; i < n; i++) {
                int x1 = locations.get(i).getKey();
                int y1 = locations.get(i).getValue();
                for (int j = 0; j < n; j++) {
                    int x2 = locations.get(j).getKey();
                    int y2 = locations.get(j).getValue();
                    distanceArray[i][j] = Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
                }
            }

            //=============================================================
            //Nearest Neighbor Construction
            //=============================================================
            System.out.println("Starting Nearest Neighbor Construction...");
            System.out.println();

            ArrayList<Solution> nnSolutions = new ArrayList<Solution>();

            for (int i = 0; i < MAX_ITR; i++) {
                Solution s = new NearestNeighborSolution();
                nnSolutions.add(s);
                s.construct();
            }

            double minDist = 999999999;
            int minIdx = -1;
            double avgDist = 0;
            double avgDuration = 0;

            for (int i = 0; i < MAX_ITR; i++) {
                Solution s = nnSolutions.get(i);

                avgDist += s.getTotalDistanceTravelled() / MAX_ITR;
                avgDuration += s.getConstructionDuration()*1.0 / MAX_ITR;

                if(s.getTotalDistanceTravelled() < minDist){
                    minDist = s.getTotalDistanceTravelled();
                    minIdx = i;
                }

                System.out.println("Printing " + i +"th solution...");
                s.printSolution();

            }

            System.out.println("Average Time required for construction in Nearest Neighbor Heuristic = " + avgDuration + "ms");
            System.out.println("Average Distance Travelled in Nearest Neighbor Heuristic = " + avgDist);
            System.out.println();

            System.out.println("Minimum Distance Solution Found in Nearest Neighbor Heuristic :");
            nnSolutions.get(minIdx).printSolution();


        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
