package com.sentilabs.coursera.algo1.week5.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 13/08/16.
 */
public class WeightedDirectGraphTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStartIndex() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        assertEquals(Integer.valueOf(0), graph.addVertex());
        graph = new WeightedDirectGraph(10, 0);
        assertEquals(Integer.valueOf(10), graph.addVertex());
    }

    @Test
    public void testCreateVerticesAtCreation() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph(10, 10);
        assertEquals(Integer.valueOf(20), graph.addVertex());
    }

    @Test
    public void testAddVertex() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        assertEquals(Integer.valueOf(0), graph.addVertex());
    }

    @Test
    public void testAddEdgeAndGetEdgesForVertex() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        graph.addEdge(vertex1, vertex2, 10L);
        Map<Integer, Long> edgesForVertex1 = graph.getEdgesForVertex(vertex1);
        Map<Integer, Long> edgesForVertex2 = graph.getEdgesForVertex(vertex2);
        assertTrue(edgesForVertex1.size() == 1);
        assertTrue(edgesForVertex2.size() == 0);
        assertTrue(edgesForVertex1.containsKey(vertex2));
        assertTrue(edgesForVertex1.get(vertex2).equals(Long.valueOf(10)));

        Integer vertex3 = graph.addVertex();
        graph.addEdge(vertex2, vertex3, 15L);
        edgesForVertex1 = graph.getEdgesForVertex(vertex1);
        edgesForVertex2 = graph.getEdgesForVertex(vertex2);
        Map<Integer, Long> edgesForVertex3 = graph.getEdgesForVertex(vertex3);
        assertTrue(edgesForVertex1.size() == 1);
        assertTrue(edgesForVertex2.size() == 1);
        assertTrue(edgesForVertex3.size() == 0);
        assertTrue(edgesForVertex1.containsKey(vertex2));
        assertTrue(edgesForVertex1.get(vertex2).equals(Long.valueOf(10)));
        assertTrue(edgesForVertex2.containsKey(vertex3));
        assertTrue(edgesForVertex2.get(vertex3).equals(Long.valueOf(15)));
    }

    @Test
    public void testGetVertices() throws Exception {
        WeightedDirectGraph graph = new WeightedDirectGraph();
        Integer vertex1 = graph.addVertex();
        Set<Integer> vertices = graph.getVertices();
        assertEquals(1, vertices.size());
        Integer vertex2 = graph.addVertex();
        assertEquals(2, vertices.size());
        vertices = graph.getVertices();
        assertTrue(vertices.contains(vertex1));
        assertTrue(vertices.contains(vertex2));
    }
}