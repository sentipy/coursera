package com.sentilabs.coursera.algo1.week4.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 04/08/16.
 */
public class DirectedEdgeTest {

    @Test
    public void testGetFrom() throws Exception {
        assertEquals(Integer.valueOf(0), new DirectedEdge(0, 1).getFrom());
    }

    @Test
    public void testGetTo() throws Exception {
        assertEquals(Integer.valueOf(1), new DirectedEdge(0, 1).getTo());
    }
}