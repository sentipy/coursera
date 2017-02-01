package com.sentilabs.coursera.algo1.week4.graph;

import java.util.*;

/**
 * Created by sentipy on 04/08/16.
 */
public class DirectedGraph {

    private int nextVertexId = 0;
    private final Map<Integer, Map<Integer, Integer>> verticesWithEdges = new HashMap<>();

    public DirectedGraph() {

    }

    public DirectedGraph(int startIndex, int createVertices) {
        nextVertexId = startIndex;
        for (int i = 0; i < createVertices; ++i) {
            addVertex();
        }
    }

    public Integer addVertex() {
        Integer ret = nextVertexId++;
        verticesWithEdges.put(ret, new HashMap<>());
        return ret;
    }

    public void addEdge(Integer from, Integer to) {
        Map<Integer, Integer> edgesFrom = verticesWithEdges.get(from);
        Map<Integer, Integer> edgesTo = verticesWithEdges.get(to);
        if (edgesFrom == null) {
            throw new IllegalArgumentException("Vertex with id " + from + " does not exist!");
        }
        if (edgesTo == null) {
            throw new IllegalArgumentException("Vertex with id " + to + " does not exist!");
        }
        Integer count = edgesFrom.get(to);
        if (count == null) {
            count = 1;
        }
        else {
            count = count + 1;
        }
        edgesFrom.put(to, count);
    }

    public Set<Integer> getVertices() {
        return Collections.unmodifiableSet(verticesWithEdges.keySet());
    }

    public Map<Integer, Integer> getEdgesForVertex(Integer vertex) {
        Map<Integer, Integer> edges = verticesWithEdges.get(vertex);
        if (edges == null) {
            throw new IllegalArgumentException("No such vertex with id = " + vertex);
        }
        return Collections.unmodifiableMap(edges);
    }
}
