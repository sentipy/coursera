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
public class LastElementAsPivotChooserTest {

    private LastElementAsPivotChooser<Object> lastElementAsPivotChooser;

    @Before
    public void setUp() throws Exception {
        lastElementAsPivotChooser = new LastElementAsPivotChooser<>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testChoosePivot() throws Exception {
        List<Object> list = new ArrayList<>();
        Object o = new Object();
        list.add(o);
        lastElementAsPivotChooser.choosePivot(list);
        assertEquals(1, list.size());
        assertSame(o, list.get(0));
        for (int i = 0; i < 5; ++i) {
            Object o1 = new Object();
            list.add(o1);
            lastElementAsPivotChooser.choosePivot(list);
            assertSame(o1, list.get(0));
        }
    }
}