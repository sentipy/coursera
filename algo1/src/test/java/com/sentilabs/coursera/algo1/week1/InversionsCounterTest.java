package com.sentilabs.coursera.algo1.week1;

import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by sentipy on 17/07/16.
 */
public class InversionsCounterTest {

    private Comparator<Integer> comparator = Integer::compare;

    @Before
    public void setUp() throws Exception {

    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testCountInversionsNullList() throws Exception {
        InversionsCounter.countInversions(null, null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testCountInversionsNullComparator() throws Exception {
        InversionsCounter.countInversions(Collections.emptyList(), null);
    }

    @org.junit.Test
    public void testCountInversionsSize0() throws Exception {
        long countInversions = InversionsCounter.countInversions(Collections.emptyList(), comparator);
        assertEquals(0, countInversions);
    }

    @org.junit.Test
    public void testCountInversionsSize1() throws Exception {
        long countInversions = InversionsCounter.countInversions(Arrays.asList(1), comparator);
        assertEquals(0, countInversions);
    }

    @org.junit.Test
    public void testCountInversionsSize2WithoutInversions() throws Exception {
        long countInversions = InversionsCounter.countInversions(Arrays.asList(1, 2), comparator);
        assertEquals(0, countInversions);
    }

    @org.junit.Test
    public void testCountInversionsSize2WithInversions() throws Exception {
        long countInversions = InversionsCounter.countInversions(Arrays.asList(2, 1), comparator);
        assertEquals(1, countInversions);
    }

    @org.junit.Test
    public void testCountInversionsSize3WithInversions() throws Exception {
        long countInversions = InversionsCounter.countInversions(Arrays.asList(1, 3, 2), comparator);
        assertEquals(1, countInversions);
        countInversions = InversionsCounter.countInversions(Arrays.asList(3, 1, 2), comparator);
        assertEquals(2, countInversions);
        countInversions = InversionsCounter.countInversions(Arrays.asList(3, 2, 1), comparator);
        assertEquals(3, countInversions);
    }

    @org.junit.Test
    public void testCountInversionsComplex() throws Exception {
        long countInversions = InversionsCounter.countInversions(Arrays.asList(1, 3, 5, 2, 4, 6), comparator);
        assertEquals(3, countInversions);

        countInversions = InversionsCounter.countInversions(Arrays.asList(6, 5, 4, 3, 2, 1), comparator);
        assertEquals(15, countInversions);

        countInversions = InversionsCounter.countInversions(Arrays.asList(1, 3, 5, 2, 4), comparator);
        assertEquals(3, countInversions);

        countInversions = InversionsCounter.countInversions(Arrays.asList(1, 3, 5, 2, 6), comparator);
        assertEquals(2, countInversions);
    }
}