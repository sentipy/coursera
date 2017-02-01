package com.sentilabs.coursera.algo1.week2.pivotchoosers;

import java.util.Comparator;
import java.util.List;

/**
 * Created by sentipy on 25/07/16.
 */
public interface IPivotChooser<T> {

    void choosePivot(List<T> list);
}
