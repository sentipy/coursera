package com.sentilabs.coursera.algo1.week3.graph;

/**
 * Created by sentipy on 30/07/16.
 */
public class UndirectedEdge {

    private final Integer vertex1;
    private final Integer vertex2;

    public UndirectedEdge(Integer vertex1, Integer vertex2) {
        if (vertex1 == null) {
            throw new IllegalArgumentException("Vertex1 must not be null!");
        }
        if (vertex2 == null) {
            throw new IllegalArgumentException("Vertex2 must not be null!");
        }
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Integer getVertex1() {
        return vertex1;
    }

    public Integer getVertex2() {
        return vertex2;
    }

    public Integer getOther(Integer vertex) {
        if (vertex1.equals(vertex)) {
            return vertex2;
        }
        return vertex1;
    }

    public boolean isSame(Integer vertex1, Integer vertex2) {
        if (this.vertex1.equals(vertex1)) {
            return this.vertex2.equals(vertex2);
        }
        else if (this.vertex1.equals(vertex2)) {
            return this.vertex2.equals(vertex1);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UndirectedEdge that = (UndirectedEdge) o;

        return isSame(that.vertex1, that.vertex2);
    }

    @Override
    public int hashCode() {
        int result = vertex1.hashCode();
        result = 31 * result + vertex2.hashCode();
        return result;
    }
}
