package com.sentilabs.coursera.algo1.week6;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sentipy on 17/08/16.
 */
public class Algo1Week6 {

    public static void main(String[] args) throws Exception {
        URI uri = Algo1Week6.class.getClassLoader().getResource("algo1/week6/algo1-programming_prob-2sum.txt").toURI();
        Path path = Paths.get(uri);
        long startReading = System.currentTimeMillis();
        List<String> strings = Files.readAllLines(path);
        long finishReading = System.currentTimeMillis();
        System.out.println("Reading took " + (finishReading - startReading) / 1000.0 + " seconds");
        long startTransform = System.currentTimeMillis();
        List<Long> longs = strings.stream().map(Long::valueOf).collect(Collectors.toList());
        long finishTransform = System.currentTimeMillis();
        System.out.println("Transform took " + (finishTransform - startTransform) / 1000.0 + " seconds");
        long startCounting = System.currentTimeMillis();
        System.out.println("Answer is " + TwoSumInRangeCounter.count(longs, -10000, 10000));
        long finishCounting = System.currentTimeMillis();
        System.out.println("Counting took " + (finishCounting - startCounting) / 1000.0 + " seconds");
    }
}
