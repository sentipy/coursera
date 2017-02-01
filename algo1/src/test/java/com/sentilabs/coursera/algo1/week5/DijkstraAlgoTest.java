package com.sentilabs.coursera.algo1.week5;

import com.sentilabs.coursera.algo1.week5.graph.WeightedDirectGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 13/08/16.
 */
public class DijkstraAlgoTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCalculateShortestPaths1Node() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex = graph.addVertex();
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, vertex);
        assertTrue(shortestPaths.size() == 1);
        assertTrue(shortestPaths.containsKey(vertex));
        assertTrue(shortestPaths.get(vertex).equals(Long.valueOf(0)));
    }

    @Test
    public void testCalculateShortestPaths2Nodes() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        graph.addEdge(vertex1, vertex2, 3L);
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, vertex1);
        assertTrue(shortestPaths.size() == 2);
        assertTrue(shortestPaths.containsKey(vertex1));
        assertTrue(shortestPaths.get(vertex1).equals(Long.valueOf(0)));
        assertTrue(shortestPaths.containsKey(vertex2));
        assertTrue(shortestPaths.get(vertex2).equals(Long.valueOf(3)));
    }

    @Test
    public void testCalculateShortestPaths3NodesTree() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        graph.addEdge(vertex1, vertex2, 3L);
        graph.addEdge(vertex1, vertex3, 4L);
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, vertex1);
        assertTrue(shortestPaths.size() == 3);
        assertTrue(shortestPaths.containsKey(vertex1));
        assertTrue(shortestPaths.get(vertex1).equals(Long.valueOf(0)));

        assertTrue(shortestPaths.containsKey(vertex2));
        assertTrue(shortestPaths.get(vertex2).equals(Long.valueOf(3)));

        assertTrue(shortestPaths.containsKey(vertex3));
        assertTrue(shortestPaths.get(vertex3).equals(Long.valueOf(4)));
    }

    @Test
    public void testCalculateShortestPaths3NodesChain() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        graph.addEdge(vertex1, vertex2, 3L);
        graph.addEdge(vertex2, vertex3, 5L);
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, vertex1);
        assertTrue(shortestPaths.size() == 3);
        assertTrue(shortestPaths.containsKey(vertex1));
        assertTrue(shortestPaths.get(vertex1).equals(Long.valueOf(0)));

        assertTrue(shortestPaths.containsKey(vertex2));
        assertTrue(shortestPaths.get(vertex2).equals(Long.valueOf(3)));

        assertTrue(shortestPaths.containsKey(vertex3));
        assertTrue(shortestPaths.get(vertex3).equals(Long.valueOf(8)));
    }

    @Test
    public void testCalculateShortestPaths4Diamond1() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        Integer vertex4 = graph.addVertex();
        graph.addEdge(vertex1, vertex2, 3L);
        graph.addEdge(vertex1, vertex3, 4L);
        graph.addEdge(vertex2, vertex4, 6L);
        graph.addEdge(vertex3, vertex4, 7L);
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, vertex1);
        assertTrue(shortestPaths.size() == 4);
        assertTrue(shortestPaths.containsKey(vertex1));
        assertEquals(Long.valueOf(0), shortestPaths.get(vertex1));

        assertTrue(shortestPaths.containsKey(vertex2));
        assertEquals(Long.valueOf(3), shortestPaths.get(vertex2));

        assertTrue(shortestPaths.containsKey(vertex3));
        assertEquals(Long.valueOf(4), shortestPaths.get(vertex3));

        assertTrue(shortestPaths.containsKey(vertex4));
        assertEquals(Long.valueOf(9), shortestPaths.get(vertex4));
    }

    @Test
    public void testCalculateShortestPaths4Diamond2() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        Integer vertex4 = graph.addVertex();
        graph.addEdge(vertex1, vertex2, 4L);
        graph.addEdge(vertex1, vertex3, 3L);
        graph.addEdge(vertex2, vertex4, 7L);
        graph.addEdge(vertex3, vertex4, 6L);
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, vertex1);
        assertTrue(shortestPaths.size() == 4);
        assertTrue(shortestPaths.containsKey(vertex1));
        assertEquals(Long.valueOf(0), shortestPaths.get(vertex1));

        assertTrue(shortestPaths.containsKey(vertex2));
        assertEquals(Long.valueOf(4), shortestPaths.get(vertex2));

        assertTrue(shortestPaths.containsKey(vertex3));
        assertEquals(Long.valueOf(3), shortestPaths.get(vertex3));

        assertTrue(shortestPaths.containsKey(vertex4));
        assertEquals(Long.valueOf(9), shortestPaths.get(vertex4));
    }

    @Test
    public void testCalculateShortestPaths4AllEdgesBestDirect() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        Integer vertex4 = graph.addVertex();
        graph.addEdge(vertex1, vertex2, 3L);
        graph.addEdge(vertex1, vertex3, 4L);
        graph.addEdge(vertex2, vertex4, 6L);
        graph.addEdge(vertex3, vertex4, 7L);
        graph.addEdge(vertex2, vertex3, 5L);
        graph.addEdge(vertex1, vertex4, 5L);
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, vertex1);
        assertTrue(shortestPaths.size() == 4);
        assertTrue(shortestPaths.containsKey(vertex1));
        assertEquals(Long.valueOf(0), shortestPaths.get(vertex1));

        assertTrue(shortestPaths.containsKey(vertex2));
        assertEquals(Long.valueOf(3), shortestPaths.get(vertex2));

        assertTrue(shortestPaths.containsKey(vertex3));
        assertEquals(Long.valueOf(4), shortestPaths.get(vertex3));

        assertTrue(shortestPaths.containsKey(vertex4));
        assertEquals(Long.valueOf(5), shortestPaths.get(vertex4));
    }

    @Test
    public void testCalculateShortestPaths4AllEdgesBestLong() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        Integer vertex4 = graph.addVertex();
        graph.addEdge(vertex1, vertex2, 1L);
        graph.addEdge(vertex1, vertex3, 4L);
        graph.addEdge(vertex2, vertex4, 6L);
        graph.addEdge(vertex3, vertex4, 1L);
        graph.addEdge(vertex2, vertex3, 1L);
        graph.addEdge(vertex1, vertex4, 5L);
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, vertex1);
        assertTrue(shortestPaths.size() == 4);
        assertTrue(shortestPaths.containsKey(vertex1));
        assertEquals(Long.valueOf(0), shortestPaths.get(vertex1));

        assertTrue(shortestPaths.containsKey(vertex2));
        assertEquals(Long.valueOf(1), shortestPaths.get(vertex2));

        assertTrue(shortestPaths.containsKey(vertex3));
        assertEquals(Long.valueOf(2), shortestPaths.get(vertex3));

        assertTrue(shortestPaths.containsKey(vertex4));
        assertEquals(Long.valueOf(3), shortestPaths.get(vertex4));
    }
}