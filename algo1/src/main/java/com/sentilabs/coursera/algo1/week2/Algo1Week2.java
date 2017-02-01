package com.sentilabs.coursera.algo1.week2;

import com.sentilabs.coursera.algo1.week2.pivotchoosers.PivotChooserType;
import com.sentilabs.coursera.algo1.week2.pivotchoosers.PivotChooserFactory;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sentipy on 25/07/16.
 */
public class Algo1Week2 {

    public static void main(String[] args) throws Exception {
        URI uri = Algo1Week2.class.getClassLoader().getResource("algo1/week2/QuickSort.txt").toURI();
        Path path = Paths.get(uri);
        List<String> strings = Files.readAllLines(path);
        List<Integer> integers = strings.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        long countPivotFirstElement = QuickSortComparisonCounter.count(new ArrayList<>(integers)
                , PivotChooserFactory.createPivotChooser(PivotChooserType.FIRST_ELEMENT, null)
                , Integer::compare);
        System.out.println("Comparisons using first element as pivot = " + countPivotFirstElement);

        long countPivotLastElement = QuickSortComparisonCounter.count(new ArrayList<>(integers)
                , PivotChooserFactory.createPivotChooser(PivotChooserType.LAST_ELEMENT, null)
                , Integer::compare);
        System.out.println("Comparisons using last element as pivot = " + countPivotLastElement);

        long countPivotMiddle = QuickSortComparisonCounter.count(new ArrayList<>(integers)
                , PivotChooserFactory.createPivotChooser(PivotChooserType.MIDDLE_OF_THREE, Integer::compare)
                , Integer::compare);
        System.out.println("Comparisons using middle of three as pivot element = " + countPivotMiddle);
    }
}
