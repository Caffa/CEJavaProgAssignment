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
import java.io.IOException;
import java.util.ArrayList;

public class ProcessManagement {

    //set the working directory
//    private static File currentDirectory = new File("/home/jit/progassignment1/java/");

    static String myCurrentDir = System.getProperty("user.dir");
//    private static File currentDirectory = new File("/Users/Caffae/GitHub/Labs/Assignment/src/main/java/com/example/assignment");
    private static File currentDirectory = new File(myCurrentDir);

    //set the instructions file
//    private static File instructionSet = new File("testproc.txt");
    private static File instructionSet = new File("test4.txt");

    public static Object lock =new Object();

    public static void main(String[] args) throws InterruptedException {

        //parse the instruction file and construct a data structure, stored inside ProcessGraph class
        ParseFile.generateGraph(new File(currentDirectory + "/"+ instructionSet));

        // Print the graph information
        for(ProcessGraphNode node:ProcessGraph.nodes){
            for(ProcessGraphNode child:node.getChildren()){
                child.addParent(node);
            }
        }
        ProcessGraph.printGraph();

	// WRITE YOUR CODE
        // Using index of ProcessGraph, loop through each ProcessGraphNode, to check whether it is ready to run
        // check if all the nodes are executed
        // WRITE YOUR CODE









        boolean allE = false;

        //Declare process builder outside of while loop and set it's directory
        ProcessBuilder pb= new ProcessBuilder();
        pb.directory(currentDirectory);

        ArrayList<ProcessGraphNode> runnableNodes;

        while(allE == false){
            //check if all nodes are executed
            allE = areAllExecuted();

            //here is a function to decide whether a node is runnable & make a list
            runnableNodes = setRunnables();

            for(ProcessGraphNode i : runnableNodes){
                //redirect as required, as long as it isn't std.. redirect
                if(i != null && i.isRunnable()){
                    if (!((i.getInputFile().toString()).equals("stdin"))) {
                        pb.redirectInput(i.getInputFile());
                    }
                    if (!(i.getOutputFile().toString().equals("stdout"))) {
                        pb.redirectOutput(i.getOutputFile());
                    }

                    String[] command = {"bash","-c",i.getCommand().toString()};//curr.getCommand().toString()
                    pb.command(command);
                    try {
                        Process p=pb.start();
                        p.waitFor();
                        i.setExecuted();
                        i.setNotRunable();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
//
//            //run node if runnable
//            for(ProcessGraphNode i : runnableNodes){
//                //make a thread for each
//                //then do run for each --- this can be put in process graph node
//                //then end?
//                if(i != null){
//                    i.start();
//                }
//
//            }
//
//            for(ProcessGraphNode i : runnableNodes){
//                //make a thread for each
//                //then do run for each --- this can be put in process graph node
//                //then end?
//
//
//                if(i != null){
//                    i.join();
//                }
//            }








            //print basic graph
//            ProcessGraph.printBasic();

        }

//        System.out.println("Completed - check against output");
        // Print the graph information
        ProcessGraph.printGraph();



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
