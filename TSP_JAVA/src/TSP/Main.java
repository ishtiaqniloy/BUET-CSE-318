package TSP;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

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

            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }




        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
