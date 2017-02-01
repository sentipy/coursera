package com.sentilabs.coursera.algo1.week3;

import com.sentilabs.coursera.algo1.week3.graph.UndirectedGraph;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sentipy on 30/07/16.
 */
public class Algo1Week3 {

    private static UndirectedGraph createGraphFromMap(Map<Integer, List<Integer>> map) {
        UndirectedGraph undirectedGraph = new UndirectedGraph(1, map.size());
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            for (Integer dest : entry.getValue()) {
                Integer key = entry.getKey();
                if (key.compareTo(dest) < 0 && map.get(key).contains(dest)) {
                    continue;
                }
                undirectedGraph.addEdge(key, dest);
            }
        }
        return undirectedGraph;
    }

    public static void main(String[] args) throws Exception {
        URI uri = Algo1Week3.class.getClassLoader().getResource("algo1/week3/kargerMinCut.txt").toURI();
        Path path = Paths.get(uri);
        List<String> strings = Files.readAllLines(path);
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        for (String string : strings) {
            String[] split = string.split("\t");
            Integer vertexId = Integer.valueOf(split[0]);
            List<Integer> adjVertices = new ArrayList<>(split.length - 1);
            graphMap.put(vertexId, adjVertices);
            for (int i = 1; i < split.length; ++i) {
                adjVertices.add(Integer.valueOf(split[i]));
            }
        }
        int size = graphMap.size();
        long trials = size * size * (int)Math.log(size);
        int bestMin = RandomContraction.run(createGraphFromMap(graphMap));
        System.out.println("1st try, min = " + bestMin);
        for (long i = 0; i < trials; ++i) {
            int thisRunMin = RandomContraction.run(createGraphFromMap(graphMap));
            if (thisRunMin < bestMin) {
                bestMin = thisRunMin;
            }
            System.out.println("Run #" + i + ", min for this run = " + thisRunMin + ", best = " + bestMin);
        }
    }
}
