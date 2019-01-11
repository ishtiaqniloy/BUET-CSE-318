package TSP;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.System.exit;

class Savings extends Solution{
    private int startCityIdx;

    private ArrayList<ArrayList<Integer>> adjacencyList;
    private ArrayList<Integer> startArray;
    private ArrayList <Pair<Pair<Integer, Integer>, Float>> savings;

    long startTime;

    Savings() {
        super();

        if (Main.RANDOMIZED_START){
            startCityIdx =  Main.rand.nextInt(Main.n);
            startCityIdx = 94;
        }
        else{
            startCityIdx = 0;
        }

        System.out.println("Starting City: " + startCityIdx);

        adjacencyList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < Main.n; i++) {
            adjacencyList.add(new ArrayList<Integer>());
        }

        startArray = new ArrayList<Integer>();
        savings = new ArrayList <Pair<Pair<Integer, Integer>, Float>>(); //((i,j),saving)

    }

    private void calculateSavings(){
        //=============================================================
        //Calculating Savings
        //=============================================================
        System.out.println("Started Calculating Savings");

        savings.clear();
        for (int i = 0; i < Main.n; i++) {
            if(i==startCityIdx){
                continue;
            }
            float distIK = Main.calculateDistance(i, startCityIdx);
            for (int j = i+1; j < Main.n; j++) {
                if (j==startCityIdx || i==j){
                    continue;
                }
                float distJK = Main.calculateDistance(j, startCityIdx);
                float distIJ = Main.calculateDistance(i, j);
                float save = distIK + distJK - distIJ;
                savings.add(new Pair<>(new Pair<>(i,j), save));
            }
        }
        System.out.println("Finished Calculating Savings");


        //=============================================================
        //Sorting Savings
        //=============================================================
        System.out.println("Started Sorting Savings");
        savings.sort(new Comparator<Pair<Pair<Integer, Integer>, Float>>() {
            @Override
            public int compare(Pair<Pair<Integer, Integer>, Float> o2, Pair<Pair<Integer, Integer>, Float> o1) {
                return Float.compare(o1.getValue(), o2.getValue()) ;
            }
        });
        System.out.println("Finished Sorting Savings");


        //=============================================================
        //Printing Sorted Savings
        //=============================================================
//        for (Pair<Pair<Integer, Integer>, Float> entry: savings) {
//            System.out.println( entry.getKey().getKey() + " " +  entry.getKey().getValue() + " " + entry.getValue());
//        }
    }

    private void initializeAdjacencyList(){
        //=============================================================
        //Connecting Every Node with Starting Node
        //=============================================================
        for (int i = 0; i < Main.n; i++) {
            if(i==startCityIdx){
                continue;
            }
            ///considering each edge bidirectional for implementation purpose
            adjacencyList.get(startCityIdx).add(i);
            adjacencyList.get(i).add(startCityIdx);

            //2nd edge
            adjacencyList.get(i).add(startCityIdx);
            adjacencyList.get(startCityIdx).add(i);

            //for avoiding local loop
            startArray.add(i);

        }

//        System.out.println("Printing Initial Adjacency Lists");
//        printAdjacencyList();
    }

    private void updateAdjacencyList(){
        //=============================================================
        //Updating Adjacency Lists According to Savings
        //=============================================================

        System.out.println("Iterating Sorted Savings Array to Update Adjacency Lists");
        for (Pair<Pair<Integer, Integer>, Float> entry: savings) {
            int i = entry.getKey().getKey();
            int j = entry.getKey().getValue();
//            System.out.println();
//            System.out.println(i + " " + j);

            if(!startArray.contains(i) && !startArray.contains(j)){
                //System.out.println("Will become disjoint");
                continue;
            }
            else if(!adjacencyList.get(startCityIdx).contains(i) || !adjacencyList.get(startCityIdx).contains(j)){
                //System.out.println("Not enough edges to remove");
                continue;
            }
            else if(adjacencyList.get(startCityIdx).size()<=2){
                //System.out.println("Finish");
                break;
            }

            updateGraph(i, j);

//            System.out.println("Printing Changed Adjacency Lists for " + i + " " + j);
//            printAdjacencyList();

        }

        System.out.println("Printing Updated Adjacency Lists");
        printAdjacencyList();

    }

    private void correctAdjacencyList(){
        ArrayList<Integer> tempArray = adjacencyList.get(startCityIdx);

        if(tempArray.size()==2){
            buildTourArray();
            return;
        }

        int num = (tempArray.size()/2)-1;

//        System.out.println("Before Testing start array size:");
//        printAdjacencyList();

        //=============================================================
        //Finding all possible edges
        //=============================================================
        ArrayList <Pair<Integer, Integer>> allPairs = new ArrayList<Pair<Integer, Integer>>();

        for (int i = 0; i < tempArray.size()-1; i++) {
            for (int j = i+1; j < tempArray.size(); j++) {
                allPairs.add(new Pair<Integer, Integer>(tempArray.get(i),tempArray.get(j)));
            }
        }

        System.out.println("All Pairs: ");
        for (Pair<Integer, Integer> entry: allPairs) {
            System.out.println("<" + entry.getKey() + "," + entry.getValue() + ">");
        }


        //=============================================================
        //Correcting Adjacency List
        //=============================================================

        ArrayList <Pair<Integer, Integer>> edges = new ArrayList<Pair<Integer, Integer>>();
        ArrayList <Integer> takenPoints = new ArrayList<Integer>();
        for (int i = num; i < allPairs.size(); i++) { //take num edges from first i entries
            if(num==0){
                break;
            }

            boolean []tempFlags = new boolean[i]; //array to find combination of num edges from i entries
            for (int j = 0; j < num; j++) {
                tempFlags[j] = true;            //initially take first j entries
            }
            for (int j = num; j < i; j++) {
                tempFlags[j] = false;
            }

            boolean flag = true;

            while(flag){
                flag = false;
                int k = i-1;
                edges.clear();
                takenPoints.clear();

                while(k>=0 && tempFlags[k]){ //clearing consecutive 1's from right
                    k--;
                }
                while(k>=0 && !tempFlags[k]){ //clearing consecutive 0's after consecutive 1's from right
                    k--;
                }
                if(k>=0){
                    flag = true;
                }
                else {
                    continue;
                }

                tempFlags[k] = false;
                tempFlags[k+1] = true;

                boolean doThisIteration = true;
                for (int j = 0; j < i; j++) {
                    if (tempFlags[j]){
                        Pair<Integer, Integer> edge = allPairs.get(j);
                        if (takenPoints.contains(edge.getKey()) || takenPoints.contains(edge.getKey()) ){
                            doThisIteration = false;
                            break;
                        }
                        edges.add(edge);
                        takenPoints.add(edge.getKey());
                        takenPoints.add(edge.getValue());
                    }
                }

                if(doThisIteration){
                    System.out.println("trying with num i k : " + num + " " + i + " " + k);
                    System.out.print("EDGES: ");
                    for (Pair<Integer, Integer> edge: edges ) {
                        System.out.print("<" + edge.getKey() + "," + edge.getValue() +"> ");
                    }
                    System.out.println();

                    updateGraph(edges);
                    buildTourArray();
                    System.out.println("CHECKING HERE: ");
                    printAdjacencyList();
                    boolean success = checkSolution();
                    if(success){
                        break;
                    }
                    else {
                        clearTour();
                        revertGraph(edges);
                    }

                    printAdjacencyList();
                }
                else {
//                    System.out.println("Iteration Skipped with num i k : " + num + " " + i + " " + k);
//                    System.out.print("EDGES: ");
//                    for (Pair<Integer, Integer> edge: edges ) {
//                        System.out.print("<" + edge.getKey() + "," + edge.getValue() +"> ");
//                    }
//                    System.out.println();
                }

            }

//            clearTour();
//            buildTourArray();
//            if(checkSolution()){
//                break;
//            }



        }

        System.out.println("Printing Corrected Adjacency Lists");
        printAdjacencyList();

    }

    private void updateGraph(int i, int j){
        //removing 2 bidirectional edges
        adjacencyList.get(i).remove(new Integer(startCityIdx));
        adjacencyList.get(startCityIdx).remove(new Integer(i));

        adjacencyList.get(startCityIdx).remove(new Integer(j));
        adjacencyList.get(j).remove(new Integer(startCityIdx));

        startArray.remove(new Integer(i));
        startArray.remove(new Integer(j));

        //add bidirectional edge between i and j
        adjacencyList.get(i).add(j);
        adjacencyList.get(j).add(i);
    }

    private void updateGraph(ArrayList<Pair<Integer, Integer>> edges){
        for (Pair<Integer, Integer> entry: edges) {
            int i = entry.getKey();
            int j = entry.getValue();

            updateGraph(i,j);

        }

    }

    private void revertGraph(int i, int j){
        adjacencyList.get(i).add(startCityIdx);
        adjacencyList.get(startCityIdx).add(i);

        adjacencyList.get(startCityIdx).add(j);
        adjacencyList.get(j).add(startCityIdx);

        //remove bidirectional edge between i and j
        adjacencyList.get(i).remove(new Integer(j));
        adjacencyList.get(j).remove(new Integer(i));


    }

    private void revertGraph(ArrayList<Pair<Integer, Integer>> edges){
        for (Pair<Integer, Integer> entry: edges) {
            int i = entry.getKey();
            int j = entry.getValue();

            revertGraph(i, j);
        }
    }

    private void buildTourArray(){
        tourCityIdx.add(startCityIdx);
        int currentCityIdx = startCityIdx;

        while (tourCityIdx.size()!=Main.n){
            ArrayList<Integer> list = adjacencyList.get(currentCityIdx);

            int nextCityIdx = list.get(0);
            if(tourCityIdx.contains(nextCityIdx)){
                nextCityIdx = list.get(1);
            }

            tourCityIdx.add(nextCityIdx);
            totalDistanceTravelled += Main.calculateDistance(currentCityIdx, nextCityIdx);
            numberOfRoadsTravelled++;

            currentCityIdx = nextCityIdx;

        }

        //going back to start city
        tourCityIdx.add(startCityIdx);
        totalDistanceTravelled +=  Main.calculateDistance(currentCityIdx, startCityIdx);
        numberOfRoadsTravelled++;
    }

    private void clearTour(){
        tourCityIdx.clear();
        totalDistanceTravelled = 0;
        numberOfRoadsTravelled = 0;

    }

    private void printAdjacencyList(){
        for (int n1 = 0; n1 < Main.n; n1++) {
            System.out.print(n1 + ": ");
            for (int n2: adjacencyList.get(n1) ) {
                System.out.print(n2 + " ");
            }
            System.out.println();
        }
        System.out.print("Start Array: ");
        for (int n2: startArray ) {
            System.out.print(n2 + " ");
        }
        System.out.println();
    }



    @Override
    void construct() {
        System.out.println("Starting Construction in Savings Heuristic");
        startTime = System.currentTimeMillis();

        calculateSavings();

        initializeAdjacencyList();

        updateAdjacencyList();

        correctAdjacencyList();


        if(!checkSolution()){
            System.out.println("******WRONG CONSTRUCTION******");
            printConstruction();
            exit(0);
        }

        constructionDuration = System.currentTimeMillis() - startTime;

        System.out.println("Finished Construction in Savings Heuristic");
        System.out.println();

        //printConstruction();
    }

    @Override
    void improve() {
        //doing nothing here
    }

}
