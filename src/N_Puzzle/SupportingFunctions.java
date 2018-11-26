package N_Puzzle;

import javafx.util.Pair;
import org.omg.CORBA.MARSHAL;

public class SupportingFunctions {

    public static boolean isSolvable(int array[][]){
        return true;
    }

    public static int[][] swap(int array[][], int i1, int j1, int i2, int j2){
        int temp = array[i1][j1];
        array[i1][j1] = array[i2][j2];
        array[i2][j2] = temp;

        return array;
    }

    public static int[][] swap(int array[][], Pair<Integer, Integer> pos1, Pair<Integer, Integer> pos2){
        int temp = array[pos1.getKey()][pos1.getValue()];
        array[pos1.getKey()][pos1.getValue()] = array[pos2.getKey()][pos2.getValue()];
        array[pos2.getKey()][pos2.getValue()] = temp;

        return array.clone();
    }

    public static int getCorrectValue(Pair<Integer, Integer> pos){
        if(pos.getKey()==Main.k-1 && pos.getValue()==Main.k-1 ){
            return Main.SPACE;
        }
        else{
            return Main.k*pos.getKey()+pos.getValue()+1;
        }
    }

    public static int getHammingDistance(int array[][]){
        int dist = 0;

        for(int i=0; i<Main.k; i++){
            for (int j=0; j<Main.k; j++) {
                if(i==Main.k-1 && j==Main.k-1){
                    continue;
                }
                else if(array[i][j]!=getCorrectValue(new Pair<Integer, Integer>(i,j))){
                    dist++;
                }
            }
        }

        return dist;
    }



}
