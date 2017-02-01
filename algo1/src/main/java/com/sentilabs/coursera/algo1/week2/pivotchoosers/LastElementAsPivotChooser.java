package com.sentilabs.coursera.algo1.week2.pivotchoosers;

import java.util.List;

/**
 * Created by sentipy on 25/07/16.
 */
public class LastElementAsPivotChooser<T> implements IPivotChooser<T> {

    @Override
    public void choosePivot(List<T> list) {
        T t = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.set(list.size() - 1, t);
    }
}
