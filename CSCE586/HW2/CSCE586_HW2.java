package HW2;

import java.util.Arrays;

class Graph {
        char[] V = {'a','b','c','d','e','f','g'};
        int[][] Adj = {{0, 1, 1, 1, 1, 0, 0},
                       {1, 0, 0, 1, 0, 1, 0},
                       {1, 0, 0, 0, 0, 0, 1},
                       {1, 1, 0, 0, 0, 1, 0},
                       {1, 0, 0, 0, 0, 0, 1},
                       {0, 1, 0, 1, 0, 0, 0},
                       {0, 0, 1, 0, 1, 0, 0}};
    
    public static void main(String[] args) {
        Graph G = new Graph();
        System.out.println(Arrays.toString(G.Adj[0]));
    }        
}
