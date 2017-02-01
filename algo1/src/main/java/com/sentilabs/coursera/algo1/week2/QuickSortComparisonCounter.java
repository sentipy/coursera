package com.sentilabs.coursera.algo1.week2;

import com.sentilabs.coursera.algo1.week2.pivotchoosers.IPivotChooser;

import java.util.Comparator;
import java.util.List;

/**
 * Created by sentipy on 25/07/16.
 */
public class QuickSortComparisonCounter {

    private static<T> long countInner(List<T> list, IPivotChooser<T> pivotChooser, Comparator<T> comparator) {
        if (list.size() < 2) {
            return 0;
        }
        pivotChooser.choosePivot(list);
        int size = list.size();
        long comparisons = list.size() - 1;
        T pivot = list.get(0);
        int i = 1;
        for (int j = 1; j < size; ++j) {
            T current = list.get(j);
            if (comparator.compare(current, pivot) < 0) {
                T temp = list.get(i);
                list.set(i, current);
                list.set(j, temp);
                ++i;
            }
        }
        T temp = list.get(i - 1);
        list.set(0, temp);
        list.set(i - 1, pivot);
        return comparisons
                + count(list.subList(0, i - 1), pivotChooser, comparator)
                + count(list.subList(i, list.size()), pivotChooser, comparator);
    }

    public static<T> long count(List<T> list, IPivotChooser<T> pivotChooser, Comparator<T> comparator) {
        if (list == null) {
            throw new IllegalArgumentException("list must be set!");
        }
        if (pivotChooser == null) {
            throw new IllegalArgumentException("pivotChooser must be set!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator must be set!");
        }
        return countInner(list, pivotChooser, comparator);
    }
}
