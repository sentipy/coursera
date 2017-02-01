package com.sentilabs.coursera.algo1.week6;

import java.util.*;

/**
 * Created by sentipy on 17/08/16.
 */
public class TwoSumInRangeCounter {

    public static long count(List<Long> list, int start, int end) {
        if (list == null) {
            throw new IllegalArgumentException("List of integers cannot be null");
        }
        if (list.size() == 0) {
            return 0;
        }
        //Set<Long> set = new HashSet<>(list);
        TreeSet<Long> set = new TreeSet<>(list);
        Set<Long> result = new HashSet<>(list.size());
        for (Long firstLong : set) {
            Long startLookup = start - firstLong;
            Long endLookup = end - firstLong;
            NavigableSet<Long> subSet = set.subSet(startLookup, true, endLookup, true);
            for (Long secondLong : subSet) {
                if (!firstLong.equals(secondLong)) {
                    result.add(firstLong + secondLong);
                }
            }
            /*for (long checkSum = start; checkSum <= end; ++checkSum) {
                Long lookup = checkSum - firstLong;
                if (lookup.equals(firstLong)) {
                    continue;
                }
                if (set.contains(lookup)) {
                    result.add(checkSum);
                }
            }*/
        }
        return result.size();
    }
}
