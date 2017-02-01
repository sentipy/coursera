package com.sentilabs.coursera.algo1.week2.pivotchoosers;

import java.util.Comparator;
import java.util.List;

/**
 * Created by sentipy on 25/07/16.
 */
public class MiddleOfThreeAsPivotChooser<T> implements IPivotChooser<T> {

    private final Comparator<T> comparator;

    public MiddleOfThreeAsPivotChooser(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void choosePivot(List<T> list) {
        T first = list.get(0);
        int lastIndex = list.size() - 1;
        T last = list.get(lastIndex);
        int middleIndex = (list.size() - 1) / 2;
        T middle = list.get(middleIndex);
        // first < middle
        if (comparator.compare(first, middle) < 0) {
            // first < middle < last
            if (comparator.compare(middle, last) < 0) {
                list.set(0, middle);
                list.set(middleIndex, first);
            }
            // first < last < middle
            else if (comparator.compare(first, last) < 0) {
                list.set(0, last);
                list.set(lastIndex, first);
            }
            // last < first < middle
            else {
                // nothing to do
            }
        }
        // middle < first
        else {
            // middle < first < last
            if (comparator.compare(first, last) < 0) {
                // nothing to do
            }
            // last < middle < first
            else if (comparator.compare(last, middle) < 0) {
                list.set(0, middle);
                list.set(middleIndex, first);
            }
            // middle < last < first
            else {
                list.set(0, last);
                list.set(lastIndex, first);
            }
        }
    }
}
