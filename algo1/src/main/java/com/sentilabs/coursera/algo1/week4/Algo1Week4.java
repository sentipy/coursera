package com.sentilabs.coursera.algo1.week4;

import com.sentilabs.coursera.algo1.week4.graph.DirectedGraph;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by sentipy on 04/08/16.
 */
public class Algo1Week4 {

    public static void main(String[] args) throws Exception {
        URI uri = Algo1Week4.class.getClassLoader().getResource("algo1/week4/SCC.txt").toURI();
        Path path = Paths.get(uri);
        List<String> strings = Files.readAllLines(path);
        final int startIndex = 1;
        final int size = 875714;
        DirectedGraph directedGraph = new DirectedGraph(startIndex, size);
        for (String string : strings) {
            String[] split = string.split(" ");
            directedGraph.addEdge(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
        }
        List<List<Integer>> SCCs = KosarajuSCC.findSCCs(directedGraph);
        Collections.sort(SCCs, (o1, o2) -> Integer.compare(o1.size(), o2.size()));
        System.out.println("found SCCs = " + SCCs.size());
        for (List<Integer> scc : SCCs) {
            System.out.println("size = " + scc.size()/* + ", items " + scc*/);
        }
    }
}
