/*
*  Programming Assignment 1
*  Author : Wang Yee Lin Pamela
∗ ID: 1002367
∗ Date: 03/03/2018
*
*
* */

package com.example.assignment;


import java.io.File;
import java.util.ArrayList;

public class ProcessManagement {

    //set the working directory
//    private static File currentDirectory = new File("/home/jit/progassignment1/java/");

    static String myCurrentDir = System.getProperty("user.dir");
//    private static File currentDirectory = new File("/Users/Caffae/GitHub/Labs/Assignment/src/main/java/com/example/assignment");
    private static File currentDirectory = new File(myCurrentDir);

    //set the instructions file
//    private static File instructionSet = new File("testproc.txt");
    private static File instructionSet = new File("test1.txt");

    public static Object lock =new Object();

    public static void main(String[] args) throws InterruptedException {

        //parse the instruction file and construct a data structure, stored inside ProcessGraph class
        ParseFile.generateGraph(new File(currentDirectory + "/"+ instructionSet));

        // Print the graph information
        ProcessGraph.printGraph();

	// WRITE YOUR CODE
        // Using index of ProcessGraph, loop through each ProcessGraphNode, to check whether it is ready to run
        // check if all the nodes are executed
        // WRITE YOUR CODE









        boolean allE = false;

        while(allE == false){
            //check if all nodes are executed
            allE = areAllExecuted();

            //here is a function to decide whether a node is runnable & make a list
            ArrayList<ProcessGraphNode> runnableNodes = setRunnables();

            //run node if runnable
            for(ProcessGraphNode i : runnableNodes){
                //make a thread for each
                //then do run for each --- this can be put in process graph node
                //then end?
                if(i != null){
                    i.start();
                }

            }

            for(ProcessGraphNode i : runnableNodes){
                //make a thread for each
                //then do run for each --- this can be put in process graph node
                //then end?


                if(i != null){
                    i.join();
                }
            }






            //print basic graph
//            ProcessGraph.printBasic();

        }

//        System.out.println("Completed - check against output");
        // Print the graph information
        ProcessGraph.printGraph();


                //mark all the runnable nodes
	        // WRITE YOUR CODE

                //run the node if it is runnable
	        // WRITE YOUR CODE

        System.out.println("All process finished successfully");
    }



    private static boolean areAllExecuted() {
        boolean aE = true;
        for(ProcessGraphNode a : ProcessGraph.nodes){
            if(a.isExecuted() == false){
                aE = false;
            }
        }
        return aE;
    }

    private static ArrayList<ProcessGraphNode> setRunnables() {
        ArrayList<ProcessGraphNode> listToRun = new ArrayList<>();
        for(ProcessGraphNode a : ProcessGraph.nodes){
            if(a.myParentExecuted() && a.isExecuted() == false){
                a.setRunnable();
                listToRun.add(a);
            }else{
                a.setNotRunable();
            }
        }
        return listToRun;
    }

}
