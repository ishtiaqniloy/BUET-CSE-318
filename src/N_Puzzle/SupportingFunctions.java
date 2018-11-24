package N_Puzzle;

import org.omg.CORBA.MARSHAL;

public class SupportingFunctions {

    public static int[][] swap(int array[][], int i1, int j1, int i2, int j2){
        int temp = array[i1][j1];
        array[i1][j1] = array[i2][j2];
        array[i2][j2] = temp;

        return array;
    }

    public static int getCorrectValue(int i, int j){
        if(i==Main.k-1 && j==Main.k-1 ){
            return Main.SPACE;
        }
        else{
            return Main.k*i+j+1;
        }
    }

    public static int getHammingDistance(int array[][]){
        int dist = 0;

        for(int i=0; i<Main.k; i++){
            for (int j=0; j<Main.k; j++) {
                if(i==Main.k-1 && j==Main.k-1){
                    continue;
                }
                else if(array[i][j]!=getCorrectValue(i,j)){
                    dist++;
                }
            }
        }

        return dist;
    }



}
