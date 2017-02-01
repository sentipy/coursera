package com.sentilabs.coursera.algo1.week5.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sentipy on 13/08/16.
 */
public class WeightedDirectGraph {

    private int nextVertexId = 0;
    private final Map<Integer, Map<Integer, Long>> verticesWithEdges = new HashMap<>();

    public WeightedDirectGraph() {

    }

    public WeightedDirectGraph(int startIndex, int createVertices) {
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

    public void addEdge(Integer from, Integer to, Long weight) {
        Map<Integer, Long> edgesFrom = verticesWithEdges.get(from);
        Map<Integer, Long> edgesTo = verticesWithEdges.get(to);
        if (edgesFrom == null) {
            throw new IllegalArgumentException("Vertex with id " + from + " does not exist!");
        }
        if (edgesTo == null) {
            throw new IllegalArgumentException("Vertex with id " + to + " does not exist!");
        }
        Long curWeight = edgesFrom.get(to);
        if (curWeight == null || curWeight.longValue() > weight.longValue()) {
            edgesFrom.put(to, weight);
        }
    }

    public Set<Integer> getVertices() {
        return Collections.unmodifiableSet(verticesWithEdges.keySet());
    }

    public Map<Integer, Long> getEdgesForVertex(Integer vertex) {
        Map<Integer, Long> edges = verticesWithEdges.get(vertex);
        if (edges == null) {
            throw new IllegalArgumentException("No such vertex with id = " + vertex);
        }
        return Collections.unmodifiableMap(edges);
    }
}
