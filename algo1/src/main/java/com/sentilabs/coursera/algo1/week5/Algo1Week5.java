package com.sentilabs.coursera.algo1.week5;

import com.sentilabs.coursera.algo1.week5.graph.WeightedDirectGraph;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by sentipy on 13/08/16.
 */
public class Algo1Week5 {

    public static void main(String[] args) throws Exception {
        URI uri = Algo1Week5.class.getClassLoader().getResource("algo1/week5/dijkstraData.txt").toURI();
        Path path = Paths.get(uri);
        List<String> strings = Files.readAllLines(path);
        final int startIndex = 1;
        WeightedDirectGraph graph = new WeightedDirectGraph(startIndex, strings.size());
        for (String string : strings) {
            String[] split = string.split("\t");
            Integer from = Integer.valueOf(split[0]);
            for (int i = 1; i < split.length; ++i) {
                String[] toAndWeight = split[i].split(",");
                Integer to = Integer.valueOf(toAndWeight[0]);
                Long weight = Long.valueOf(toAndWeight[1]);
                graph.addEdge(from, to, weight);
            }
        }
        Map<Integer, Long> shortestPaths = DijkstraAlgo.calculateShortestPaths(graph, 1);
        Integer[] to = {7,37,59,82,99,115,133,165,188,197};
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Integer integer : to) {
            stringJoiner.add(shortestPaths.get(integer).toString());
        }
        System.out.println(stringJoiner.toString());
    }
}
