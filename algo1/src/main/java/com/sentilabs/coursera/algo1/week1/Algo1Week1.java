package com.sentilabs.coursera.algo1.week1;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sentipy on 17/07/16.
 */
public class Algo1Week1 {

    public static void main(String[] args) throws Exception {
        //InputStream inputStream = Algo1Week1.class.getClassLoader().getResourceAsStream("algo1/week1/data.txt");
        //String string = IOUtils.toString(inputStream);
        URI uri = Algo1Week1.class.getClassLoader().getResource("algo1/week1/data.txt").toURI();
        Path path = Paths.get(uri);
        List<String> strings = Files.readAllLines(path);
        List<Integer> integers = strings.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        long countInversions = InversionsCounter.countInversions(integers, Integer::compare);
        System.out.println(countInversions);
    }
}
