package com.sentilabs.coursera.algo1.week6;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by sentipy on 17/08/16.
 */
public class MedianMaintainer {

    private final PriorityQueue<Long> minHeap = new PriorityQueue<>((Comparator<Long>) Long::compareTo);
    private final PriorityQueue<Long> maxHeap = new PriorityQueue<>((Comparator<Long>) (long1, long2) -> long2.compareTo(long1));

    public void add(Long item) {
        if (minHeap.size() > 0 && minHeap.peek() > item) {
            maxHeap.add(item);
            if (maxHeap.size() - minHeap.size() == 2) {
                minHeap.add(maxHeap.poll());
            }
        }
        else {
            minHeap.add(item);
            if (minHeap.size() - maxHeap.size() == 2) {
                maxHeap.add(minHeap.poll());
            }
        }
    }

    public Long getMedian() {
        if (minHeap.size() - maxHeap.size() == 1) {
            return minHeap.peek();
        }
        return maxHeap.peek();
    }
}
