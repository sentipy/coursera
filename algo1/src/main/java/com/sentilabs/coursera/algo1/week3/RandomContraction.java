package com.sentilabs.coursera.algo1.week3;

import com.sentilabs.coursera.algo1.week3.graph.UndirectedEdge;
import com.sentilabs.coursera.algo1.week3.graph.UndirectedGraph;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;

/**
 * Created by sentipy on 30/07/16.
 */
public class RandomContraction {

    public static void contractRandomEdge(UndirectedGraph graph) {
        List<UndirectedEdge> allEdges = graph.getAllEdges();
        SecureRandom secureRandom = new SecureRandom();
        int randomInt = secureRandom.nextInt(allEdges.size());
        UndirectedEdge edge = allEdges.get(randomInt);
        contractEdge(graph, edge);
    }

    public static void contractEdge(UndirectedGraph graph, UndirectedEdge edge) {
        Integer vertex1 = edge.getVertex1();
        Integer vertex2 = edge.getVertex2();
        Collection<UndirectedEdge> edgesForVertex = graph.getEdgesForVertex(vertex2);
        graph.removeVertex(vertex2);
        for (UndirectedEdge undirectedEdge : edgesForVertex) {
            Integer other = undirectedEdge.getOther(vertex2);
            if (!other.equals(vertex1)) {
                graph.addEdge(vertex1, other);
            }
        }
    }

    public static int run(UndirectedGraph graph) {
        while (graph.getVertexCount() > 2) {
            contractRandomEdge(graph);
        }
        return graph.getAllEdges().size();
    }
}
