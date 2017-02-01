package com.sentilabs.coursera.algo1.week2.pivotchoosers;

import java.util.Comparator;

/**
 * Created by sentipy on 25/07/16.
 */
public class PivotChooserFactory {

    public static<T> IPivotChooser createPivotChooser(PivotChooserType pivotChooserType, Comparator<T> comparator) {
        switch (pivotChooserType) {
            case FIRST_ELEMENT:
                return new FirstElementAsPivotChooser<T>();
            case LAST_ELEMENT:
                return new LastElementAsPivotChooser<T>();
            case MIDDLE_OF_THREE:
                return new MiddleOfThreeAsPivotChooser<>(comparator);
            default:
                throw new IllegalArgumentException("Unknown pivot chooser type = " + pivotChooserType);
        }
    }
}
