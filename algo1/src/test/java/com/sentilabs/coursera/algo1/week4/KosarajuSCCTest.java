package com.sentilabs.coursera.algo1.week4;

import com.sentilabs.coursera.algo1.week4.graph.DirectedGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 06/08/16.
 */
public class KosarajuSCCTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindSCCs1Vertex() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        List<List<Integer>> SCCs = KosarajuSCC.findSCCs(directedGraph);
        assertEquals(1, SCCs.size());
        assertEquals(1, SCCs.get(0).size());
        assertEquals(vertex1, SCCs.get(0).get(0));
    }

    @Test
    public void testFindSCCs2Vertices() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        Integer vertex2 = directedGraph.addVertex();
        directedGraph.addEdge(vertex1, vertex2);
        List<List<Integer>> SCCs = KosarajuSCC.findSCCs(directedGraph);
        assertEquals(2, SCCs.size());
        List<Integer> first = SCCs.get(0);
        List<Integer> second = SCCs.get(1);
        assertEquals(1, first.size());
        assertEquals(1, second.size());
        assertTrue(first.get(0).equals(vertex1) || second.get(0).equals(vertex1));
        assertTrue(first.get(0).equals(vertex2) || second.get(0).equals(vertex2));
    }

    /**
     * 1 -> 2 -> 3 -> 1
     * @throws Exception
     */
    @Test
    public void testFindSCCs3VerticesCircle() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        Integer vertex2 = directedGraph.addVertex();
        Integer vertex3 = directedGraph.addVertex();
        directedGraph.addEdge(vertex1, vertex2);
        directedGraph.addEdge(vertex2, vertex3);
        directedGraph.addEdge(vertex3, vertex1);
        List<List<Integer>> SCCs = KosarajuSCC.findSCCs(directedGraph);
        assertEquals(1, SCCs.size());
        assertEquals(3, SCCs.get(0).size());
        assertTrue(SCCs.get(0).containsAll(Arrays.asList(vertex1, vertex2, vertex3)));
    }

    /**
     * 1 -> 2 -> 3 -> 1
     * 2 -> 4
     * @throws Exception
     */
    @Test
    public void testFindSCCs4VerticesCircle() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        Integer vertex2 = directedGraph.addVertex();
        Integer vertex3 = directedGraph.addVertex();
        Integer vertex4 = directedGraph.addVertex();
        directedGraph.addEdge(vertex1, vertex2);
        directedGraph.addEdge(vertex2, vertex3);
        directedGraph.addEdge(vertex3, vertex1);
        directedGraph.addEdge(vertex2, vertex4);
        List<List<Integer>> SCCs = KosarajuSCC.findSCCs(directedGraph);
        assertEquals(2, SCCs.size());
        List<Integer> biggestSize = SCCs.get(0);
        List<Integer> smallestSize = SCCs.get(1);
        if (smallestSize.size() > biggestSize.size()) {
            biggestSize = SCCs.get(1);
            smallestSize = SCCs.get(0);
        }
        assertEquals(3, biggestSize.size());
        assertEquals(1, smallestSize.size());
        assertTrue(biggestSize.containsAll(Arrays.asList(vertex1, vertex2, vertex3)));
        assertFalse(biggestSize.contains(vertex4));

        assertTrue(smallestSize.contains(vertex4));
    }

    /**
     * 1 -> 2 -> 3 -> 1
     * 2 -> 4
     * 4 -> 5 -> 6 -> 4
     * @throws Exception
     */
    @Test
    public void testFindSCCs6Vertices2Circles() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        Integer vertex2 = directedGraph.addVertex();
        Integer vertex3 = directedGraph.addVertex();
        Integer vertex4 = directedGraph.addVertex();
        Integer vertex5 = directedGraph.addVertex();
        Integer vertex6 = directedGraph.addVertex();
        directedGraph.addEdge(vertex1, vertex2);
        directedGraph.addEdge(vertex2, vertex3);
        directedGraph.addEdge(vertex3, vertex1);
        directedGraph.addEdge(vertex2, vertex4);
        directedGraph.addEdge(vertex4, vertex5);
        directedGraph.addEdge(vertex5, vertex6);
        directedGraph.addEdge(vertex6, vertex4);
        List<List<Integer>> SCCs = KosarajuSCC.findSCCs(directedGraph);
        assertEquals(2, SCCs.size());
        List<Integer> first = SCCs.get(0);
        List<Integer> second = SCCs.get(1);
        assertEquals(3, first.size());
        assertEquals(3, second.size());
        assertTrue(first.containsAll(Arrays.asList(vertex1, vertex2, vertex3)) || second.containsAll(Arrays.asList(vertex1, vertex2, vertex3)));
        assertTrue(first.containsAll(Arrays.asList(vertex4, vertex5, vertex6)) || second.containsAll(Arrays.asList(vertex4, vertex5, vertex6)));

    }

    @Test
    public void testCreateReverseGraph() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        // check graph with only one vertex
        DirectedGraph reverseGraph = KosarajuSCC.createReverseGraph(directedGraph);
        assertEquals(1, reverseGraph.getVertices().size());
        assertEquals(0, reverseGraph.getEdgesForVertex(vertex1).size());

        // add new vertex
        Integer vertex2 = directedGraph.addVertex();
        // add edge 1 -> 2
        directedGraph.addEdge(vertex1, vertex2);
        reverseGraph = KosarajuSCC.createReverseGraph(directedGraph);
        assertEquals(2, reverseGraph.getVertices().size());
        // in reverse graph edge 1 -> 2 must NOT exist
        assertEquals(0, reverseGraph.getEdgesForVertex(vertex1).size());
        // in reverse graph edge 2 -> 1 must exist
        assertEquals(1, reverseGraph.getEdgesForVertex(vertex2).size());
        assertTrue(reverseGraph.getEdgesForVertex(vertex2).containsKey(vertex1));

        // add new vertex
        Integer vertex3 = directedGraph.addVertex();
        // add edge 2 -> 3
        directedGraph.addEdge(vertex2, vertex3);
        reverseGraph = KosarajuSCC.createReverseGraph(directedGraph);
        assertEquals(3, reverseGraph.getVertices().size());
        // in reverse graph edge 1 -> 2 must NOT exist
        assertEquals(0, reverseGraph.getEdgesForVertex(vertex1).size());
        // in reverse graph edge 2 -> 3 must NOT exist
        assertEquals(1, reverseGraph.getEdgesForVertex(vertex2).size());
        assertFalse(reverseGraph.getEdgesForVertex(vertex2).containsKey(vertex3));
        // in reverse graph edge 2 -> 1 must exist
        assertEquals(1, reverseGraph.getEdgesForVertex(vertex2).size());
        assertTrue(reverseGraph.getEdgesForVertex(vertex2).containsKey(vertex1));
        // in reverse graph edge 3 -> 2 must exist
        assertEquals(1, reverseGraph.getEdgesForVertex(vertex3).size());
        assertTrue(reverseGraph.getEdgesForVertex(vertex3).containsKey(vertex2));

        // add edge 3 -> 1
        directedGraph.addEdge(vertex3, vertex1);
        reverseGraph = KosarajuSCC.createReverseGraph(directedGraph);
        // in reverse graph edge 1 -> 3 must exist
        assertEquals(1, reverseGraph.getEdgesForVertex(vertex1).size());
        assertTrue(reverseGraph.getEdgesForVertex(vertex1).containsKey(vertex3));
    }
}