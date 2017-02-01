package com.sentilabs.coursera.algo1.week4.graph;

/**
 * Created by sentipy on 04/08/16.
 */
public class DirectedEdge {

    private final Integer from;
    private final Integer to;

    public DirectedEdge(Integer from, Integer to) {
        this.from = from;
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }
}
