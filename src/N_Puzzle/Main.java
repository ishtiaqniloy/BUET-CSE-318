package N_Puzzle;

import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static int k;
    public static int n;
    public static final int SPACE = -1;

    public static int array[][];

    public static void main(String[] args) {
        Solution solution;

        try {
            File input = new File("Input.txt");
            if(input.exists()){
                System.out.println("Input file found!");
            }
            else{
                System.out.println("Input file not found. Exiting...");
                return;
            }

            Scanner scanner = new Scanner(new FileInputStream(input));

            k = scanner.nextInt();
            scanner.nextLine();

            array = new int[k][k];
            Pair<Integer, Integer> sp = new Pair<Integer, Integer>(-1,-1);

            //taking array input
            for(int i=0; i<k; i++){
                String str = scanner.nextLine();
                //System.out.println(str);
                int j=0;
                for (j=0; j<k && 2*j < str.length(); j++){
                    if(str.charAt(2*j)==' '){
                        array[i][j] = SPACE;
                        sp = new Pair<Integer, Integer>(i,j);
                    }
                    else {
                        array[i][j] = str.charAt(2*j)-'0';
                    }
                }
                if(j!=k){
                    array[i][k-1] = SPACE;
                    sp = new Pair<Integer, Integer>(i,k-1);
                }

            }
            Node initialNode = new Node(array, 0, SupportingFunctions.getHammingDistance(array), "Initial Node", null, sp);
            initialNode.printNode();

//            array = SupportingFunctions.swap(array, new Pair<Integer, Integer>(0,0), new Pair<Integer, Integer>(2,2));
//            initialNode = new Node(array, 0, SupportingFunctions.getHammingDistance(array), "Initial Node", null, sp);
//            initialNode.printNode();

            //System.out.println(sp.getKey() + " " + sp.getValue());

//            for (Node node: initialNode.getChildren()) {
//                node.printNode();
//            }

            solution = new HamDist(initialNode);
            Node resultHam = solution.solve();

            if(resultHam==null){
                System.out.println("Unexpectedly solution not found with Hamming Distance");
            }
            else {
                System.out.println("Printing solution steps with Hamming Distance: ");
                resultHam.printSteps();

            }





        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
