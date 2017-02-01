package com.sentilabs.coursera.algo1.week6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 17/08/16.
 */
public class TwoSumInRangeCounterTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountNull() throws Exception {
        TwoSumInRangeCounter.count(null, 0, 0);
    }

    @Test
    public void testCountOneElement() throws Exception {
        long result = TwoSumInRangeCounter.count(Collections.singletonList(1L), 0, 0);
        assertEquals(0, result);
    }

    @Test
    public void testCountTwoElementsSame() throws Exception {
        long result = TwoSumInRangeCounter.count(Arrays.asList(1L, 1L), 0, 0);
        assertEquals(0, result);
    }

    @Test
    public void testCountTwoElementsInRange() throws Exception {
        long result = TwoSumInRangeCounter.count(Arrays.asList(1L, 2L), 3, 3);
        assertEquals(1, result);
    }

    @Test
    public void testCountTwoElementsOutOfRange() throws Exception {
        long result = TwoSumInRangeCounter.count(Arrays.asList(1L, 2L), 0, 2);
        assertEquals(0, result);
    }

    @Test
    public void testCountThreeElementsSomeInRange() throws Exception {
        long result = TwoSumInRangeCounter.count(Arrays.asList(1L, 2L, 3L), 3, 3);
        assertEquals(1, result);
        result = TwoSumInRangeCounter.count(Arrays.asList(1L, 2L, 3L), 3, 4);
        assertEquals(2, result);
        result = TwoSumInRangeCounter.count(Arrays.asList(1L, 2L, 3L), 4, 4);
        assertEquals(1, result);
    }

    @Test
    public void testCountThreeElementsAllInRange() throws Exception {
        long result = TwoSumInRangeCounter.count(Arrays.asList(1L, 2L, 3L), 0, 5);
        assertEquals(3, result);
    }
}