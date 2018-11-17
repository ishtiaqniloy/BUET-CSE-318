package Missionaries_Cannibals;

import java.util.Objects;

public class Node {
    int left_m;
    int left_c;
    boolean boatInLeftSide;

    public Node(int left_m, int left_c, boolean boatInLeftSide) {
        this.left_m = left_m;
        this.left_c = left_c;
        this.boatInLeftSide = boatInLeftSide;
    }

    public int getLeft_m() {
        return left_m;
    }

    public void setLeft_m(int left_m) {
        this.left_m = left_m;
    }

    public int getLeft_c() {
        return left_c;
    }

    public void setLeft_c(int left_c) {
        this.left_c = left_c;
    }

    public boolean isBoatInLeftSide() {
        return boatInLeftSide;
    }

    public void setBoatInLeftSide(boolean boatInLeftSide) {
        this.boatInLeftSide = boatInLeftSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return left_m == node.left_m &&
                left_c == node.left_c &&
                boatInLeftSide == node.boatInLeftSide;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left_m, left_c, boatInLeftSide);
    }

    @Override
    public String toString() {
        return "Node{" +
                "left_m=" + left_m +
                ", left_c=" + left_c +
                ", boatInLeftSide=" + boatInLeftSide +
                '}';
    }

    boolean checkValidity(){
        if(left_c>left_m || (Main.c-left_c) > (Main.m-left_m) ){
            return false;
        }
        else{
            return true;
        }
    }


}
