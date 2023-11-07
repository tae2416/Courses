package HW2;

import java.util.*;

class Graph {
    //Initialize the vertices and the adjacency matrix
    Vertex[] V = {new Vertex('a'),
                    new Vertex('b'),
                    new Vertex('c'),
                    new Vertex('d'),
                    new Vertex('e'),
                    new Vertex('f'),
                    new Vertex('g')};
    int[][] Adj = {{0, 1, 1, 1, 1, 0, 0},
                    {1, 0, 0, 1, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 1},
                    {0, 1, 0, 1, 0, 0, 0},
                    {0, 0, 1, 0, 1, 0, 0}};
    public static int time;
    
    public static void main(String[] args) {
        Graph G = new Graph();
        
        //Print Initial State
        System.out.println("");
        System.out.println("Initial Graph State");
        System.out.println("");
        for (Vertex x : G.V) {
            System.out.println("Vertex " + x.name + ": d/f time = " + x.d + "/" + x.f + ", color = " + x.color);
        }
        System.out.println("");

        //Run DFS on G
        DFS(G);

        //Print out DFS results
        System.out.println("DFS Results");
        System.out.println("");
        for (Vertex x : G.V) {
            System.out.println("Vertex " + x.name + ": d/f time = " + x.d + "/" + x.f + ", color = " + x.color);
        }
    }   
    
    public static void DFS (Graph G) {
        for (Vertex x : G.V) {
            x.color = 'W';
            x.pi = null;
        }
        time = 0;

        for (Vertex x : G.V) {
            if (x.color == 'W'){
                DFS_Visit(G,x);
            }
        }
    }

    public static void DFS_Visit (Graph G, Vertex x) {
        time += 1;
        x.d = time;
        x.color = 'G';

        List<Integer> xAdj = getADJ(G,x);
        for (int i : xAdj) {
            if (G.V[i].color == 'W') {
                G.V[i].pi = x;
                DFS_Visit(G, G.V[i]);
            }
        }
        time += 1;
        x.f = time;
        x.color = 'B';
    }

    //Returns an adjacency vertex list for the Graph G and the Vertex x in G.
    //Correlation between G.V and G.Adj is based on the vertex arrangement in G.V.
    public static List<Integer> getADJ (Graph G, Vertex x) {
        int xInd = 0;
        List<Integer> xAdj = new ArrayList<>();

        //Determines the index of the Vertex X in G.Adj
        while (xInd < G.V.length && G.V[xInd].name != x.name) {
            if (G.V[xInd].name != x.name) {
                xInd += 1;
            }
        }

        //Determines non-zero elements of the row of G.Adj that corresponds to x
        for (int i = 0; i < G.Adj[xInd].length; i++) {
            if (G.Adj[xInd][i] != 0) {
                xAdj.add(i);
            }
        }
        return xAdj;
    }
}

class Vertex {
    char name;
    char color;
    int d = 0;
    int f = 0;
    Vertex pi; 

    Vertex (char name) {
        this.name = name;
    }
}
