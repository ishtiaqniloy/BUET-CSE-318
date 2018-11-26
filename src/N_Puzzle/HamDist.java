package N_Puzzle;

import java.util.HashSet;
import java.util.PriorityQueue;

public class HamDist implements Solution {
    Node initialNode;

    HashSet <Node> expandedNodes;   //closedList
    HashSet <Node> exploredNodes;   //helps to update faster

    PriorityQueue <Node> queue;

    HamDist(Node init){
        initialNode = init;

        expandedNodes = new HashSet<Node>();
        exploredNodes = new HashSet<Node>();

        queue = new PriorityQueue<Node>(20, new NodeComparator());


    }


    @Override
    public Node solve() {

        expandedNodes.add(initialNode);

        Node test = new Node(initialNode.getArray(), 1, 2, "", initialNode, initialNode.getSpacePosition());

        System.out.println(expandedNodes.contains(test));



        return initialNode;
    }
}
