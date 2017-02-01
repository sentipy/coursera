package com.sentilabs.coursera.algo1.week4;

import com.sentilabs.coursera.algo1.week4.graph.DirectedGraph;

import java.util.*;

/**
 * Created by sentipy on 06/08/16.
 */
public class KosarajuSCC {

    public static DirectedGraph createReverseGraph(DirectedGraph directedGraph) {
        Set<Integer> vertices = directedGraph.getVertices();
        if (vertices.size() == 0) {
            return new DirectedGraph();
        }
        Optional<Integer> min = vertices.stream().min(Integer::compare);
        DirectedGraph resultGraph = new DirectedGraph(min.get(), vertices.size());
        for (Integer vertice : vertices) {
            Map<Integer, Integer> edgesForVertex = directedGraph.getEdgesForVertex(vertice);
            for (Map.Entry<Integer, Integer> toVertexEntry : edgesForVertex.entrySet()) {
                Integer toVertex = toVertexEntry.getKey();
                Integer count = toVertexEntry.getValue();
                for (int i = 0; i < count; ++i) {
                    resultGraph.addEdge(toVertex, vertice);
                }
            }
        }
        return resultGraph;
    }

    private static void DFSinner(DirectedGraph directedGraph, Set<Integer> visited, Deque<Integer> result, Integer vertex) {
        //System.out.println("visiting " + vertex);
        if (visited.contains(vertex)) {
            //System.out.println("Vertex " + vertex + " already visited");
            return;
        }
        visited.add(vertex);
        for (Map.Entry<Integer, Integer> entry : directedGraph.getEdgesForVertex(vertex).entrySet()) {
            Integer toVertex = entry.getKey();
            DFSinner(directedGraph, visited, result, toVertex);
        }
        result.addFirst(vertex);
    }

    private static Deque<Integer> DFS(DirectedGraph directedGraph) {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> result = new LinkedList<>();
        for (Integer vertex : directedGraph.getVertices()) {
            DFSinner(directedGraph, visited, result, vertex);
        }
        return result;
    }

    public static List<List<Integer>> findSCCs(DirectedGraph directedGraph) {
        DirectedGraph reverseGraph = createReverseGraph(directedGraph);
        Deque<Integer> deque = DFS(reverseGraph);
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> allSCCs = new ArrayList<>();
        while (!deque.isEmpty()) {
            Integer vertex = deque.pop();
            LinkedList<Integer> result = new LinkedList<>();
            DFSinner(directedGraph, visited, result, vertex);
            if (result.size() > 0) {
                allSCCs.add(result);
            }
        }
        return allSCCs;
    }
}
