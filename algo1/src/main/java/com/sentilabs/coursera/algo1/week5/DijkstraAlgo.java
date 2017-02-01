package com.sentilabs.coursera.algo1.week5;

import com.sentilabs.coursera.algo1.week5.graph.WeightedDirectGraph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by sentipy on 13/08/16.
 */
public class DijkstraAlgo {

    private static class PQItem {

        //private final Integer from;
        private final Integer to;
        private final Long pathLength;


        private PQItem(/*Integer from, */Integer to, Long pathLength) {
            //this.from = from;
            this.to = to;
            this.pathLength = pathLength;
        }

        /*public Integer getFrom() {
            return from;
        }*/

        public Integer getTo() {
            return to;
        }

        public Long getPathLength() {
            return pathLength;
        }
    }

    public static Map<Integer, Long> calculateShortestPaths(WeightedDirectGraph graph, Integer startVertex) {
        Map<Integer, Long> result = new HashMap<>();
        PriorityQueue<PQItem> priorityQueue = new PriorityQueue<>(
                (Comparator<PQItem>) (pq1, pq2) -> pq1.getPathLength().compareTo(pq2.getPathLength())
        );
        priorityQueue.add(new PQItem(startVertex, 0L));
        while (!priorityQueue.isEmpty()) {
            PQItem pqItem = priorityQueue.poll();
            Integer to = pqItem.getTo();
            if (result.containsKey(to)) {
                continue;
            }
            Long pathLength = pqItem.getPathLength();
            result.put(to, pathLength);
            Map<Integer, Long> edgesForVertex = graph.getEdgesForVertex(to);
            for (Map.Entry<Integer, Long> entry : edgesForVertex.entrySet()) {
                Integer curTo = entry.getKey();
                if (result.containsKey(curTo)) {
                    continue;
                }
                Long edgeLength = entry.getValue();
                priorityQueue.add(new PQItem(curTo, pathLength + edgeLength));
            }
        }
        return result;
    }
}
