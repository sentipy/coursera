package com.sentilabs.coursera.algo1.week1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sentipy on 17/07/16.
 */
public class InversionsCounter {

    private static class CountInversionsResult<T> {

        private final List<T> result;
        private final long inversions;

        private CountInversionsResult(List<T> result, long inversions) {
            this.result = result;
            this.inversions = inversions;
        }

        public List<T> getResult() {
            return result;
        }

        public long getInversions() {
            return inversions;
        }
    }

    private static<T> CountInversionsResult innerCountInversions(List<T> list, Comparator<T> comparator) {
        if (list.size() < 2) {
            return new CountInversionsResult(list, 0);
        }
        int middle = list.size() / 2;
        List<T> left = list.subList(0, middle);
        List<T> right = list.subList(middle, list.size());
        CountInversionsResult leftCountInversionsResult = innerCountInversions(left, comparator);
        CountInversionsResult rightCountInversionsResult = innerCountInversions(right, comparator);
        List<T> resultList = new ArrayList<>();
        long inversions = leftCountInversionsResult.getInversions() + rightCountInversionsResult.getInversions();
        List<T> leftList = leftCountInversionsResult.getResult();
        List<T> rightList = rightCountInversionsResult.getResult();
        int leftSize = leftList.size();
        int rightSize = rightList.size();
        int i = 0;
        int j = 0;
        while (i < leftSize && j < rightSize) {
            T fromLeft = leftList.get(i);
            T fromRight = rightList.get(j);
            int compare = comparator.compare(fromLeft, fromRight);
            if (compare < 0) {
                resultList.add(fromLeft);
                ++i;
            }
            else {
                resultList.add(fromRight);
                ++j;
                inversions += leftSize - i;
            }
        }
        if (i < leftSize) {
            resultList.addAll(leftList.subList(i, leftList.size()));
        }
        else if (j < rightSize) {
            resultList.addAll(rightList.subList(j, rightList.size()));
        }
        return new CountInversionsResult(resultList, inversions);
    }

    public static<T> long countInversions(List<T> list, Comparator<T> comparator) {
        if (list == null) {
            throw new IllegalArgumentException("list must be specified!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator must be specified!");
        }
        CountInversionsResult countInversionsResult = innerCountInversions(list, comparator);
        return countInversionsResult.getInversions();
    }
}
