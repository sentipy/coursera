package com.sentilabs.coursera.algo1.week6;

import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sentipy on 17/08/16.
 */
public class Algo1Week6_2 {

    public static void main(String[] args) throws Exception {
        URI uri = Algo1Week6_2.class.getClassLoader().getResource("algo1/week6/Median.txt").toURI();
        Path path = Paths.get(uri);
        List<String> strings = Files.readAllLines(path);
        List<Long> longs = strings.stream().map(Long::valueOf).collect(Collectors.toList());
        MedianMaintainer medianMaintainer = new MedianMaintainer();
        BigDecimal sum = BigDecimal.ZERO;
        long answer = 0;
        for (Long aLong : longs) {
            medianMaintainer.add(aLong);
            Long median = medianMaintainer.getMedian();
            sum = sum.add(BigDecimal.valueOf(median));
            answer += median;
            answer %= 10000;
        }
        System.out.println("Answer (using BigDecimal, total sum first, then modulo) is " + sum.remainder(BigDecimal.valueOf(10000L)));
        System.out.println("Answer (using long, modulo on each step) is " + answer);
    }
}
