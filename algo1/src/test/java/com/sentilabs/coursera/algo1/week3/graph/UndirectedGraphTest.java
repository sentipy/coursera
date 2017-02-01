package com.sentilabs.coursera.algo1.week3.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 30/07/16.
 */
public class UndirectedGraphTest {

    private UndirectedGraph graph;

    @Before
    public void setUp() throws Exception {
        graph = new UndirectedGraph();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStart() throws Exception {
        graph = new UndirectedGraph(10, 0);
        assertEquals(Integer.valueOf(10), graph.addVertex());
    }

    @Test
    public void testWithCount() throws Exception {
        graph = new UndirectedGraph(15);
        assertEquals(15, graph.getVertexCount());
        assertEquals(Integer.valueOf(15), graph.addVertex());
    }

    @Test
    public void testStartWithCount() throws Exception {
        graph = new UndirectedGraph(10, 10);
        assertEquals(10, graph.getVertexCount());
        assertEquals(Integer.valueOf(20), graph.addVertex());
    }

    @Test
    public void testAddVertexAndVertexCount() throws Exception {
        assertEquals(0, graph.getVertexCount());
        int vertex = graph.addVertex();
        assertEquals(0, vertex);
        assertEquals(1, graph.getVertexCount());
        vertex = graph.addVertex();
        assertEquals(1, vertex);
        assertEquals(2, graph.getVertexCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeNonExistingVertexFirst() throws Exception {
        graph.addEdge(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeNonExistingVertexSecond() throws Exception {
        graph.addVertex();
        graph.addEdge(0, 1);
    }

    @Test
    public void testAddEdge() throws Exception {
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0, 1);
        assertEquals(1, graph.getAllEdges().size());
        graph.addEdge(0, 1);
        assertEquals(2, graph.getAllEdges().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEdgesForVertexNoSuchVertex() throws Exception {
        graph.getEdgesForVertex(0);
    }

    @Test
    public void testGetEdgesForVertex() throws Exception {
        int vertex1 = graph.addVertex();
        int vertex2 = graph.addVertex();
        UndirectedEdge edge1 = graph.addEdge(vertex1, vertex2);
        Collection<UndirectedEdge> edgesForVertex = graph.getEdgesForVertex(vertex1);
        UndirectedEdge currentEdge = edgesForVertex.iterator().next();
        assertTrue(currentEdge.getVertex1() == vertex1 || currentEdge.getVertex2() == vertex1);
        assertTrue(currentEdge.getVertex1() == vertex2 || currentEdge.getVertex2() == vertex2);

        edgesForVertex = graph.getEdgesForVertex(vertex2);
        currentEdge = edgesForVertex.iterator().next();
        assertTrue(currentEdge.getVertex1() == vertex2 || currentEdge.getVertex2() == vertex2);
        assertTrue(currentEdge.getVertex1() == vertex1 || currentEdge.getVertex2() == vertex1);

        int vertex3 = graph.addVertex();
        UndirectedEdge edge3 = graph.addEdge(vertex1, vertex3);
        edgesForVertex = graph.getEdgesForVertex(vertex1);
        assertEquals(2, edgesForVertex.size());
        Iterator<UndirectedEdge> iterator = edgesForVertex.iterator();
        currentEdge = iterator.next();
        assertTrue(currentEdge == edge1 || currentEdge == edge3);
        currentEdge = iterator.next();
        assertTrue(currentEdge == edge1 || currentEdge == edge3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEdgesForVertexWrongArgument() throws Exception {
        graph.getEdgesForVertex(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveEdgeNoEdge() throws Exception {
        graph.removeEdge(0, 1);
    }

    @Test
    public void testRemoveEdgeSimple() throws Exception {
        int vertex1 = graph.addVertex();
        int vertex2 = graph.addVertex();
        UndirectedEdge edge = graph.addEdge(vertex1, vertex2);
        graph.removeEdge(edge.getVertex1(), edge.getVertex2());
        assertEquals(0, graph.getAllEdges().size());
        assertEquals(0, graph.getEdgesForVertex(vertex1).size());
        assertEquals(0, graph.getEdgesForVertex(vertex2).size());
    }

    @Test
    public void testRemoveEdgeComplex() throws Exception {
        int vertex1 = graph.addVertex();
        int vertex2 = graph.addVertex();
        int vertex3 = graph.addVertex();
        UndirectedEdge edge12 = graph.addEdge(vertex1, vertex2);
        UndirectedEdge edge13 = graph.addEdge(vertex1, vertex3);
        UndirectedEdge edge23 = graph.addEdge(vertex2, vertex3);
        graph.removeEdge(edge12.getVertex1(), edge12.getVertex2());
        assertEquals(2, graph.getAllEdges().size());
        assertEquals(1, graph.getEdgesForVertex(vertex1).size());
        assertEquals(1, graph.getEdgesForVertex(vertex2).size());
        assertEquals(2, graph.getEdgesForVertex(vertex3).size());
    }

    @Test
    public void testParallelEdges() throws Exception {
        int vertex1 = graph.addVertex();
        int vertex2 = graph.addVertex();
        UndirectedEdge edge12_1 = graph.addEdge(vertex1, vertex2);
        UndirectedEdge edge12_2 = graph.addEdge(vertex1, vertex2);
        assertEquals(2, graph.getAllEdges().size());

        int vertex3 = graph.addVertex();
        UndirectedEdge edge13 = graph.addEdge(vertex1, vertex3);
        assertEquals(3, graph.getAllEdges().size());
        UndirectedEdge edge23 = graph.addEdge(vertex2, vertex3);
        assertEquals(4, graph.getAllEdges().size());

        graph.removeEdge(vertex1, vertex2);
        assertEquals(3, graph.getAllEdges().size());
        Collection<UndirectedEdge> edgesForVertex = graph.getEdgesForVertex(vertex1);
        UndirectedEdge currentEdge = edgesForVertex.iterator().next();
        assertTrue(currentEdge.getVertex1() == vertex1 || currentEdge.getVertex2() == vertex1);
        assertTrue(currentEdge.getVertex1() == vertex2 || currentEdge.getVertex2() == vertex2);

        edgesForVertex = graph.getEdgesForVertex(vertex2);
        currentEdge = edgesForVertex.iterator().next();
        assertTrue(currentEdge.getVertex1() == vertex2 || currentEdge.getVertex2() == vertex2);
        assertTrue(currentEdge.getVertex1() == vertex1 || currentEdge.getVertex2() == vertex1);

        graph.removeEdge(vertex1, vertex2);
        assertEquals(2, graph.getAllEdges().size());
    }

    @Test
    public void testRemoveVertex() throws Exception {
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex2, vertex3);
        graph.addEdge(vertex1, vertex3);
        graph.removeVertex(vertex1);
        assertEquals(1, graph.getAllEdges().size());
        Integer vertex4 = graph.addVertex();
        graph.addEdge(vertex2, vertex4);
        graph.addEdge(vertex2, vertex4);
        graph.addEdge(vertex3, vertex4);
        graph.addEdge(vertex3, vertex4);
        assertEquals(5, graph.getAllEdges().size());
        graph.removeVertex(vertex4);
        assertEquals(1, graph.getAllEdges().size());
    }
}