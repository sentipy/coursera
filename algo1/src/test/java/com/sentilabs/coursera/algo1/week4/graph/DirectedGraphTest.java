package com.sentilabs.coursera.algo1.week4.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 04/08/16.
 */
public class DirectedGraphTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createDirectedGraphStartIndex() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer id = directedGraph.addVertex();
        assertEquals(Integer.valueOf(0), id);

        directedGraph = new DirectedGraph(10, 0);
        id = directedGraph.addVertex();
        assertEquals(Integer.valueOf(10), id);
    }

    @Test
    public void createDirectedGraphCount() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph(1, 10);
        assertEquals(10, directedGraph.getVertices().size());
        Integer vertex = directedGraph.addVertex();
        assertEquals(Integer.valueOf(11), vertex);
        assertEquals(11, directedGraph.getVertices().size());
    }

    @Test
    public void testAddVertex() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer id = directedGraph.addVertex();
        assertEquals(Integer.valueOf(0), id);
        id = directedGraph.addVertex();
        assertEquals(Integer.valueOf(1), id);

        directedGraph = new DirectedGraph(10, 0);
        id = directedGraph.addVertex();
        assertEquals(Integer.valueOf(10), id);
        id = directedGraph.addVertex();
        assertEquals(Integer.valueOf(11), id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeNoFromVertex() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        directedGraph.addEdge(vertex1 - 1, vertex1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeNoToVertex() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        directedGraph.addEdge(vertex1, vertex1 + 1);
    }

    @Test
    public void testAddEdge() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        Integer vertex2 = directedGraph.addVertex();
        directedGraph.addEdge(vertex1, vertex2);
    }

    @Test
    public void testGetVertices() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEdgesForVertexNoSuchVertex() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        directedGraph.getEdgesForVertex(0);
    }

    @Test
    public void testGetEdgesForVertex() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();
        Integer vertex1 = directedGraph.addVertex();
        Integer vertex2 = directedGraph.addVertex();

        // add edge 1 -> 2
        directedGraph.addEdge(vertex1, vertex2);
        // vertex1 must have 1 edge now
        Map<Integer, Integer> edgesForVertex1 = directedGraph.getEdgesForVertex(vertex1);
        assertEquals(1, edgesForVertex1.size());
        assertTrue(edgesForVertex1.containsKey(vertex2));

        // vertex2 must still have 0 edges
        Map<Integer, Integer> edgesForVertex2 = directedGraph.getEdgesForVertex(vertex2);
        assertEquals(0, edgesForVertex2.size());

        // add edge 2 -> 1
        directedGraph.addEdge(vertex2, vertex1);
        // vertex1 must still have 1 edge
        edgesForVertex1 = directedGraph.getEdgesForVertex(vertex1);
        assertEquals(1, edgesForVertex1.size());
        assertTrue(edgesForVertex1.containsKey(vertex2));

        // vertex2 must have 1 edge now
        edgesForVertex2 = directedGraph.getEdgesForVertex(vertex2);
        assertEquals(1, edgesForVertex2.size());
        assertTrue(edgesForVertex2.containsKey(vertex1));

        Integer vertex3 = directedGraph.addVertex();
        // add edge 1 -> 3
        directedGraph.addEdge(vertex1, vertex3);
        edgesForVertex1 = directedGraph.getEdgesForVertex(vertex1);
        // vertex1 must now have 2 edges
        assertEquals(2, edgesForVertex1.size());
        assertTrue(edgesForVertex1.containsKey(vertex2) && edgesForVertex1.containsKey(vertex3));

        // vertex2 must still have 1 edge
        edgesForVertex2 = directedGraph.getEdgesForVertex(vertex2);
        assertEquals(1, edgesForVertex2.size());
        assertTrue(edgesForVertex2.containsKey(vertex1));

        // vertex3 must still have 0 edge
        Map<Integer, Integer> edgesForVertex3 = directedGraph.getEdgesForVertex(vertex3);
        assertEquals(0, edgesForVertex3.size());
    }
}