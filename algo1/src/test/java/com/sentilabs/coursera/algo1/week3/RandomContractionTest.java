package com.sentilabs.coursera.algo1.week3;

import com.sentilabs.coursera.algo1.week3.graph.UndirectedEdge;
import com.sentilabs.coursera.algo1.week3.graph.UndirectedGraph;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 30/07/16.
 */
public class RandomContractionTest {

    private int runRandomContraction(UndirectedGraph graph) {
        return RandomContraction.run(graph);
    }

    /**
     * star means that there are two lines:
     * 1) from top left to bottom right (1 - 6)
     * 2) from top right to bottom left (2 - 5)
     * 1 - 2 - 3 - 4
     * | * |   | * |
     * 5 - 6 - 7 - 8
     * @throws Exception
     */
    @Test
    public void testContract() throws Exception {
        UndirectedGraph graph = new UndirectedGraph(1, 8);
        UndirectedEdge edge12 = graph.addEdge(1, 2);
        UndirectedEdge edge23 = graph.addEdge(2, 3);
        UndirectedEdge edge34 = graph.addEdge(3, 4);
        UndirectedEdge edge56 = graph.addEdge(5, 6);
        UndirectedEdge edge67 = graph.addEdge(6, 7);
        UndirectedEdge edge78 = graph.addEdge(7, 8);
        UndirectedEdge edge15 = graph.addEdge(1, 5);
        UndirectedEdge edge26 = graph.addEdge(2, 6);
        UndirectedEdge edge37 = graph.addEdge(3, 7);
        UndirectedEdge edge48 = graph.addEdge(4, 8);
        UndirectedEdge edge16 = graph.addEdge(1, 6);
        UndirectedEdge edge25 = graph.addEdge(2, 5);
        UndirectedEdge edge38 = graph.addEdge(3, 8);
        UndirectedEdge edge47 = graph.addEdge(4, 7);
        RandomContraction.contractEdge(graph, edge48);
        Collection<UndirectedEdge> edgesForVertex = graph.getEdgesForVertex(4);
        long count = edgesForVertex.stream().filter(undirectedEdge -> undirectedEdge.getOther(4).equals(7)).count();
        assertEquals(2, count);
    }

    @Test
    public void testRun1() throws Exception {
        UndirectedGraph graph = new UndirectedGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex2, vertex3);
        int min = runRandomContraction(graph);
        assertEquals(1, min);
    }

    @Test
    public void testRun2() throws Exception {
        UndirectedGraph graph = new UndirectedGraph();
        Integer vertex1 = graph.addVertex();
        Integer vertex2 = graph.addVertex();
        Integer vertex3 = graph.addVertex();
        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex1, vertex3);
        graph.addEdge(vertex2, vertex3);
        int min = runRandomContraction(graph);
        assertEquals(2, min);
    }

    /**
     * 1 - 2 - 5
     * |   |
     * 3 - 4
     * @throws Exception
     */
    @Test
    public void testRunComplex5_5() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 1;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 5);
            graph.addEdge(1, 2);
            graph.addEdge(2, 5);
            graph.addEdge(3, 4);
            graph.addEdge(1, 3);
            graph.addEdge(2, 4);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }

    /**
     * 1 - 2 - 5
     * |   | /
     * 3 - 4
     * @throws Exception
     */
    @Test
    public void testRunComplex5_6() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 2;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 5);
            graph.addEdge(1, 2);
            graph.addEdge(2, 5);
            graph.addEdge(3, 4);
            graph.addEdge(1, 3);
            graph.addEdge(2, 4);
            graph.addEdge(4, 5);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }

    /**
     * star means that there are two lines:
     * 1) from top left to bottom right (1 - 6)
     * 2) from top right to bottom left (2 - 5)
     * 1 - 2
     * | * |
     * 3 - 4
     * @throws Exception
     */
    @Test
    public void testRunComplex4_6() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 3;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 4);
            graph.addEdge(1, 2);
            graph.addEdge(3, 4);
            graph.addEdge(1, 3);
            graph.addEdge(2, 4);
            graph.addEdge(1, 4);
            graph.addEdge(2, 3);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }

    /**
     * 1 - 2
     * | / |
     * 3 - 4
     * @throws Exception
     */
    @Test
    public void testRunComplex4_5() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 2;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 4);
            graph.addEdge(1, 2);
            graph.addEdge(3, 4);
            graph.addEdge(1, 3);
            graph.addEdge(2, 4);
            graph.addEdge(2, 3);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }

    /**
     * 1 - 2
     * |   |
     * 3 - 4
     * @throws Exception
     */
    @Test
    public void testRunComplex4_4() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 2;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 4);
            graph.addEdge(1, 2);
            graph.addEdge(3, 4);
            graph.addEdge(1, 3);
            graph.addEdge(2, 4);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }

    /**
     * 1 - 2
     * |
     * 3 - 4
     * @throws Exception
     */
    @Test
    public void testRunComplex4_3() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 1;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 4);
            graph.addEdge(1, 2);
            graph.addEdge(3, 4);
            graph.addEdge(1, 3);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }

    /**
     * star means that there are two lines:
     * 1) from top left to bottom right (1 - 6)
     * 2) from top right to bottom left (2 - 5)
     * 1 - 2 - 3 - 4
     * | * |   | * |
     * 5 - 6 - 7 - 8
     * @throws Exception
     */
    @Test
    public void testRunComplex8_14() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 2;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 8);
            graph.addEdge(1, 2);
            graph.addEdge(2, 3);
            graph.addEdge(3, 4);
            graph.addEdge(5, 6);
            graph.addEdge(6, 7);
            graph.addEdge(7, 8);
            graph.addEdge(1, 5);
            graph.addEdge(2, 6);
            graph.addEdge(3, 7);
            graph.addEdge(4, 8);
            graph.addEdge(1, 6);
            graph.addEdge(2, 5);
            graph.addEdge(3, 8);
            graph.addEdge(4, 7);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }

    /**
     * star means that there are two lines:
     * 1) from top left to bottom right (1 - 6)
     * 2) from top right to bottom left (2 - 5)
     * 1 - 2 - 3 - 4
     * | * | * | * |
     * 5 - 6 - 7 - 8
     * @throws Exception
     */
    @Test
    public void testRunComplex8_16() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 3;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 8);
            graph.addEdge(1, 2);
            graph.addEdge(2, 3);
            graph.addEdge(3, 4);
            graph.addEdge(5, 6);
            graph.addEdge(6, 7);
            graph.addEdge(7, 8);
            graph.addEdge(1, 5);
            graph.addEdge(2, 6);
            graph.addEdge(3, 7);
            graph.addEdge(4, 8);
            graph.addEdge(1, 6);
            graph.addEdge(2, 5);
            graph.addEdge(2, 7);
            graph.addEdge(3, 6);
            graph.addEdge(3, 8);
            graph.addEdge(4, 7);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }

    /**
     *   -   -
     * 1 - 2 - 3
     *   -   -
     * @throws Exception
     */
    @Test
    public void testParallel() throws Exception {
        int i = 0;
        int bestMin = Integer.MAX_VALUE;
        int expected = 3;
        while (i < 2000 && bestMin != expected) {
            UndirectedGraph graph = new UndirectedGraph(1, 3);
            graph.addEdge(1, 2);
            graph.addEdge(1, 2);
            graph.addEdge(1, 2);
            graph.addEdge(2, 3);
            graph.addEdge(2, 3);
            graph.addEdge(2, 3);
            int run = RandomContraction.run(graph);
            if (run < bestMin) {
                bestMin = run;
            }
            ++i;
        }
        assertEquals(expected, bestMin);
    }
}