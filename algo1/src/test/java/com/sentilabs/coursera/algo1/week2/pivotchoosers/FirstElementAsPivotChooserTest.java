package com.sentilabs.coursera.algo1.week2.pivotchoosers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 25/07/16.
 */
public class FirstElementAsPivotChooserTest {

    private FirstElementAsPivotChooser<Object> firstElementAsPivotChooser;

    @Before
    public void setUp() throws Exception {
        firstElementAsPivotChooser = new FirstElementAsPivotChooser<>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testChoosePivotZeroSize() throws Exception {
        List<Object> list = new ArrayList<>();
        firstElementAsPivotChooser.choosePivot(list);
        assertEquals(0, list.size());
    }

    @Test
    public void testChoosePivotSameElement() throws Exception {
        List<Object> list = new ArrayList<>();
        Object o = new Object();
        list.add(o);
        firstElementAsPivotChooser.choosePivot(list);
        assertEquals(1, list.size());
        assertSame(o, list.get(0));
        for (int i = 0; i < 5; ++i) {
            list.add(new Object());
            firstElementAsPivotChooser.choosePivot(list);
            assertSame(o, list.get(0));
        }
    }
}