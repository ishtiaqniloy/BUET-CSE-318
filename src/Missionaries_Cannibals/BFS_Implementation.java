package Missionaries_Cannibals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_Implementation {
    private HashMap<Node,Boolean> nodeMap = new HashMap <Node,Boolean>(); //Boolean value corresponds to isExplored
    private LinkedList<Node> nodeQueue = new LinkedList <Node>(); //BFS Queue. addLast(E e) and removeFirst() will be used as enqueue and dequeue

    private long iteration;
    private long MAX_TIME;
    public BFS_Implementation() {
        iteration = 0;

        MAX_TIME = System.currentTimeMillis() + Main.TIMEOUT_SECONDS*1000;


    }

    public Node runBFS(){

        Node initialNode = new Node(Main.m, Main.c, true, null);

        nodeMap.put(initialNode, false);
        nodeQueue.addLast(initialNode);

        while (!nodeQueue.isEmpty()){
            iteration++;

            Node u = nodeQueue.removeFirst();
            nodeMap.replace(u, true); //explored

            if(u.isGoal()){

                System.out.println();
                System.out.println("***BFS RESULT***");
                System.out.println("Number of Explored Nodes = " + iteration);
                System.out.println("Number of Expanded Nodes = " + nodeMap.size());
                System.out.println("Time required = " + ( ( System.currentTimeMillis() - MAX_TIME + Main.TIMEOUT_SECONDS*1000 ) ) + " ms");

                System.out.println();
                System.out.println("Printing path...");
                u.printPath();
                System.out.println();
                return u;
            }

            //checking maximum iteration constraint
            if(iteration > Main.MAX_ITERATIONS){
                System.out.println("Maximum iterations reached in BFS. Terminating...");
                return null;
            }

            //checking timeout constraint
            if(System.currentTimeMillis() > MAX_TIME){
                System.out.println("Timeout reached in BFS. Terminating...");
                return null;
            }


            ArrayList <Node> childNodes = u.generateChildNodes();

            for (Node v: childNodes) {
                //System.out.println("Child" + v);
                if (!nodeMap.containsKey(v)){
                    nodeMap.put(v, false);
                    nodeQueue.addLast(v);
                }
            }

        }

        //no path found from initial node to goal node
        System.out.println("Solution not found");
        return null;
    }


}
