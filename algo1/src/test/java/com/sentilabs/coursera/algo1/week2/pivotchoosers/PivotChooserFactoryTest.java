package com.sentilabs.coursera.algo1.week2.pivotchoosers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sentipy on 25/07/16.
 */
public class PivotChooserFactoryTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreatePivotChooser() throws Exception {
        IPivotChooser pivotChooser = PivotChooserFactory.createPivotChooser(PivotChooserType.FIRST_ELEMENT, null);
        assertTrue(pivotChooser instanceof FirstElementAsPivotChooser);
        pivotChooser = PivotChooserFactory.createPivotChooser(PivotChooserType.LAST_ELEMENT, null);
        assertTrue(pivotChooser instanceof LastElementAsPivotChooser);
        pivotChooser = PivotChooserFactory.createPivotChooser(PivotChooserType.MIDDLE_OF_THREE, null);
        assertTrue(pivotChooser instanceof MiddleOfThreeAsPivotChooser);
    }
}