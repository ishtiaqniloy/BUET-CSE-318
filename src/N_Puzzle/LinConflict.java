package N_Puzzle;

import java.util.HashMap;
import java.util.PriorityQueue;

public class LinConflict implements Solution {
    private Node initialNode;

    private HashMap<Node, Integer> expandedNodes;   //closedList
    private HashMap <Node, Integer> exploredNodes;   //helps to update faster

    private PriorityQueue <Node> queue;

    LinConflict(Node init){
        initialNode = init;

        expandedNodes = new  HashMap <Node, Integer>();
        exploredNodes = new  HashMap <Node, Integer>();

        queue = new PriorityQueue<Node>(20, new NodeComparator());


    }


    @Override
    public Node solve() {

        queue.add(initialNode);
        exploredNodes.put(initialNode, initialNode.getEffectiveVal());

        while (!queue.isEmpty()){

            Node node = queue.poll();
            expandedNodes.put(node, node.getEffectiveVal());

            //node.printNode();

            if(node.isGoal()){
                System.out.println("***RESULT OF LINEAR CONFLICTS***");
                System.out.println("Number of Expanded Nodes = " + expandedNodes.size());
                System.out.println("Number of Explored Nodes = " + exploredNodes.size());

                return node;
            }

            for (Node child : node.getChildrenLinear()) {
                if (expandedNodes.containsKey(child)){
                    continue;
                }
                else if(exploredNodes.containsKey(child)){
                    int oldVal = exploredNodes.get(child);
                    if (oldVal > child.getEffectiveVal()){
                        exploredNodes.replace(child, child.getEffectiveVal());
                        queue.remove(child);
                        queue.add(child);
                    }
                }
                else{
                    exploredNodes.put(child, child.getEffectiveVal());
                    queue.add(child);
                }

            }

        }


        return null;

    }

}
