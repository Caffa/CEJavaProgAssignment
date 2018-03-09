# CEJavaProgAssignment

/*
*  Programming Assignment 1
*  Author : Wang Yee Lin Pamela, Tan Jun Hui Augustine
∗ ID: 1002367, 1002158
∗ Date: 03/03/2018
*
*
* */

## Purpose of the program
The purpose of this program is to execute a group of programs that have control and data dependencies on each other in the correct order.

## How to compile
To compile this program, place the testing instruction set and the test files in the same folder as the .java files provided and compile using: javac *.java 

## How the program works
First it will set the current working directory, then it will set the instruction set to testproc.txt as per the default code given.

Next the program will parse the file and populate the data structure as provided, according to the instruction set from the testproc.txt in the same folder. 

Next the program will set up the parent pointers for every node in the data structure. Before printing out the graph information.

Process builder is initialized and it's directory is set to the current directory.
An arraylist is initialized for the list of runnable nodes

A boolean variable, allE is used to check if all nodes are executed. It controls a while loop that keeps executing the following set of actions as long as all nodes are not executed.
1. set the list of runnable nodes to all nodes that are currently runnable (parents have been executed and the node itself has not been executed before.
2. for every node in the runnable list, redirect the input and output of the node if required (if it isn't stdin or stdout) and run the command, starting the process. Wait if neccessary. Then set the current node as having been executed.
3. set allE to the boolean of whether all nodes have been executed

Once it is done print out the graph again.

## Explaining the functions

### myParentExecuted
Here is a function to check all parents have been executed
```java
    public boolean myParentExecuted() {
        boolean ans = true;
        for (ProcessGraphNode parent : this.getParents()) {
            if (!parent.isExecuted())
                ans = false;
        }

        return ans;
    }
```
    
### areAllExecuted
Here is a function to check if all nodes have been executed
```java
private static boolean areAllExecuted() {
        boolean aE = true;
        for(ProcessGraphNode a : ProcessGraph.nodes){
            if(a.isExecuted() == false){
                aE = false;
            }
        }
        return aE;
    }
```

### setRunnables
Here is a function that returns all runnable nodes 
Nodes are deemed to be runnable if their parent nodes have all been executed and they have not been executed before.
```java
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
```



