package com.sentilabs.coursera.algo1.week3.graph;

import com.google.common.collect.HashMultiset;

import java.util.*;

/**
 * Created by sentipy on 30/07/16.
 */
public class UndirectedGraph {

    private int vertexNextId = 0;
    private final Map<Integer, Collection<UndirectedEdge>> vertices = new HashMap<>();
    private final List<UndirectedEdge> allEdges = new ArrayList<>();

    public UndirectedGraph() {

    }

    /**
     *
     * @param count - amount of vertices to create
     */
    public UndirectedGraph(int count) {
        this(0, count);
    }

    /**
     *
     * @param start - id of the first vertex
     * @param count - amount of vertices to create
     */
    public UndirectedGraph(int start, int count) {
        vertexNextId = start;
        for (int i = 0; i < count; ++i) {
            this.addVertex();
        }
    }

    public Integer addVertex() {
        Integer current = vertexNextId++;
        vertices.put(current, HashMultiset.<UndirectedEdge>create());
        return current;
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public UndirectedEdge addEdge(Integer vertex1, Integer vertex2) {
        Collection<UndirectedEdge> edges1 = vertices.get(vertex1);
        if (edges1 == null) {
            throw new IllegalArgumentException("Vertex with id = " + vertex1 + " does not exist");
        }
        Collection<UndirectedEdge> edges2 = vertices.get(vertex2);
        if (edges2 == null) {
            throw new IllegalArgumentException("Vertex with id = " + vertex2 + " does not exist");
        }
        UndirectedEdge edge = new UndirectedEdge(vertex1, vertex2);
        edges1.add(edge);
        edges2.add(edge);
        allEdges.add(edge);
        return edge;
    }

    public List<UndirectedEdge> getAllEdges() {
        return Collections.unmodifiableList(allEdges);
    }

    public Collection<UndirectedEdge> getEdgesForVertex(int vertex) {
        Collection<UndirectedEdge> edges = vertices.get(vertex);
        if (edges == null) {
            throw new IllegalArgumentException("Vertex " + vertex + " does not exist");
        }
        return Collections.unmodifiableCollection(edges);
    }

    public void removeEdge(Integer vertex1, Integer vertex2) {
        UndirectedEdge undirectedEdge = new UndirectedEdge(vertex1, vertex2);
        boolean isRemoved = allEdges.remove(undirectedEdge);
        if (!isRemoved) {
            throw new IllegalArgumentException("No edges between vertex " + vertex1 + " and " + vertex2 + " found");
        }
        Collection<UndirectedEdge> edges1 = vertices.get(vertex1);
        edges1.remove(undirectedEdge);
        Collection<UndirectedEdge> edges2 = vertices.get(vertex2);
        edges2.remove(undirectedEdge);
    }

    public void removeVertex(Integer vertex) {
        Collection<UndirectedEdge> undirectedEdges = vertices.get(vertex);
        for (UndirectedEdge undirectedEdge : undirectedEdges) {
            Integer other = undirectedEdge.getOther(vertex);
            vertices.get(other).removeIf(checkEdge -> {
                Integer check = checkEdge.getOther(other);
                return check.equals(vertex);
            });
        }
        vertices.remove(vertex);
        allEdges.removeIf(undirectedEdge -> undirectedEdge.getVertex1().equals(vertex)
                || undirectedEdge.getVertex2().equals(vertex)
        );
    }
}
