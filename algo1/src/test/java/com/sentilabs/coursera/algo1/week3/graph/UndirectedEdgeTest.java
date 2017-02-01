package com.sentilabs.coursera.algo1.week3.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 30/07/16.
 */
public class UndirectedEdgeTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetVertex1() throws Exception {
        UndirectedEdge edge = new UndirectedEdge(0, 1);
        assertEquals(Integer.valueOf(0), edge.getVertex1());
    }

    @Test
    public void testGetVertex2() throws Exception {
        UndirectedEdge edge = new UndirectedEdge(0, 1);
        assertEquals(Integer.valueOf(1), edge.getVertex2());
    }

    @Test
    public void testGetOther() throws Exception {
        UndirectedEdge edge = new UndirectedEdge(0, 1);
        assertEquals(Integer.valueOf(1), edge.getOther(0));
        assertEquals(Integer.valueOf(0), edge.getOther(1));
    }
}