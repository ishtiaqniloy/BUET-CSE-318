package TSP;

import javafx.util.Pair;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int MAX_ITR = 3;
    public static final int MAX_CANDIDATES = 3;
    public static final Random rand = new Random();

    public static int n;

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

            System.out.println("Calculating Distances...");

            double [][] distanceArray = new double[n][n];
            for (int i = 0; i < n; i++) {
                int x1 = locations.get(i).getKey();
                int y1 = locations.get(i).getValue();
                for (int j = 0; j < n; j++) {
                    int x2 = locations.get(j).getKey();
                    int y2 = locations.get(j).getValue();
                    distanceArray[i][j] = Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
                }
            }

            ArrayList<Solution> nnSolutions = new ArrayList<Solution>();

            for (int i = 0; i < MAX_ITR; i++) {

            }



        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
