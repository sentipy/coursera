package com.sentilabs.coursera.algo1.week2.pivotchoosers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 26/07/16.
 */
public class MiddleOfThreeAsPivotChooserTest {

    private MiddleOfThreeAsPivotChooser<Integer> middleOfThreeAsPivotChooser;

    @Before
    public void setUp() throws Exception {
        middleOfThreeAsPivotChooser = new MiddleOfThreeAsPivotChooser<>(Integer::compare);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testChoosePivot() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        middleOfThreeAsPivotChooser.choosePivot(list);
        assertEquals(Integer.valueOf(1), list.get(0));

        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        middleOfThreeAsPivotChooser.choosePivot(list);
        assertEquals(Integer.valueOf(1), list.get(0));

        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        middleOfThreeAsPivotChooser.choosePivot(list);
        assertEquals(Integer.valueOf(2), list.get(0));

        list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        middleOfThreeAsPivotChooser.choosePivot(list);
        assertEquals(Integer.valueOf(5), list.get(0));
    }
}