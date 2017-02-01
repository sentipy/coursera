package com.sentilabs.coursera.algo1.week6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 17/08/16.
 */
public class MedianMaintainerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetMedianOneElement() throws Exception {
        MedianMaintainer medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
    }

    @Test
    public void testGetMedianTwoElements() throws Exception {
        MedianMaintainer medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());

        medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
    }

    @Test
    public void testGetMedianThreeElements() throws Exception {
        MedianMaintainer medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(3L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());

        medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(3L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());

        medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(3L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());

        medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());
        medianMaintainer.add(3L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());

        medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(3L);
        assertEquals(Long.valueOf(3), medianMaintainer.getMedian());
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());

        medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(3L);
        assertEquals(Long.valueOf(3), medianMaintainer.getMedian());
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());
    }

    @Test
    public void testGetMedianFourElements() throws Exception {
        MedianMaintainer medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(1), medianMaintainer.getMedian());
        medianMaintainer.add(3L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());
        medianMaintainer.add(4L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());

        medianMaintainer = new MedianMaintainer();
        medianMaintainer.add(4L);
        assertEquals(Long.valueOf(4), medianMaintainer.getMedian());
        medianMaintainer.add(3L);
        assertEquals(Long.valueOf(3), medianMaintainer.getMedian());
        medianMaintainer.add(2L);
        assertEquals(Long.valueOf(3), medianMaintainer.getMedian());
        medianMaintainer.add(1L);
        assertEquals(Long.valueOf(2), medianMaintainer.getMedian());
    }
}