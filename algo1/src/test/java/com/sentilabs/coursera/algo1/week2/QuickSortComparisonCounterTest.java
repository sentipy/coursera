package com.sentilabs.coursera.algo1.week2;

import com.sentilabs.coursera.algo1.week2.pivotchoosers.IPivotChooser;
import com.sentilabs.coursera.algo1.week2.pivotchoosers.PivotChooserType;
import com.sentilabs.coursera.algo1.week2.pivotchoosers.PivotChooserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 25/07/16.
 */
public class QuickSortComparisonCounterTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountInversionsNullList() throws Exception {
        QuickSortComparisonCounter.count(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountInversionsNullPivotChooser() throws Exception {
        QuickSortComparisonCounter.count(Collections.emptyList(), null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountInversionsNullComparator() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.FIRST_ELEMENT, null);
        QuickSortComparisonCounter.count(Collections.<Integer>emptyList(), pivotChooser, null);
    }

    @Test
    public void testCountZeroSize() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.FIRST_ELEMENT, null);
        assertEquals(0, QuickSortComparisonCounter.count(Collections.<Integer>emptyList(), pivotChooser, Integer::compare));
        pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.LAST_ELEMENT, null);
        assertEquals(0, QuickSortComparisonCounter.count(Collections.<Integer>emptyList(), pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize1FirstElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.FIRST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        assertEquals(0, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize1LastElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.LAST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        assertEquals(0, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize1Middle() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.MIDDLE_OF_THREE, null);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        assertEquals(0, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize2FirstElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.FIRST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertEquals(1, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(1);
        assertEquals(1, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize2LastElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.LAST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertEquals(1, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(1);
        assertEquals(1, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize2Middle() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.MIDDLE_OF_THREE, Integer::compare);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertEquals(1, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(1);
        assertEquals(1, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize3FirstElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.FIRST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(1);
        assertEquals(3, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);
        assertEquals(2, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(1);
        assertEquals(2, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize3LastElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.LAST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(1);
        assertEquals(3, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);
        assertEquals(3, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(1);
        assertEquals(3, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        assertEquals(2, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(1);
        assertEquals(3, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize3Middle() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.MIDDLE_OF_THREE, Integer::compare);
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(1);
        assertEquals(2, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);
        assertEquals(2, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(1);
        assertEquals(2, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        assertEquals(2, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(1);
        assertEquals(2, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize4FirstElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.FIRST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        assertEquals(5, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize4LastElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.LAST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        assertEquals(5, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize4Middle() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.MIDDLE_OF_THREE, Integer::compare);
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        assertEquals(4, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    @Test
    public void testCountSize5Middle() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.MIDDLE_OF_THREE, Integer::compare);
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(1);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));

        list = new ArrayList<>();
        list.add(5);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        assertEquals(6, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
    }

    /**
     * the arrays are [1], [1,2], [1,2,3], [1,2,3,4], ...
     * the corresponding comparisons are 0, 1, 3, 6, ... (sums of arithmetic progression with members 0,1,2,3,...)
     * @throws Exception
     */
    @Test
    public void testCountFirstElement() throws Exception {
        IPivotChooser<Integer> pivotChooser = PivotChooserFactory.<Integer>createPivotChooser(PivotChooserType.FIRST_ELEMENT, null);
        List<Integer> list = new ArrayList<>();
        int expected = 0;
        for (int i = 0; i < 10; ++i) {
            list.add(i);
            expected += list.size() - 1;
            assertEquals(expected, QuickSortComparisonCounter.count(list, pivotChooser, Integer::compare));
        }
    }
}