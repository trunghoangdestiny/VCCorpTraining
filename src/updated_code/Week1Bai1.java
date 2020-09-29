package updated_code;

import week1.bai1.TimeCounter;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Week1Bai1 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            Set<Integer> setTemp = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toSet());
            set1.addAll(setTemp);
            set2.addAll(setTemp);

            Random random = new Random();
            set1.addAll(random
                    .ints(10001, Integer.MAX_VALUE)
                    .distinct()
                    .limit(190000)
                    .boxed()
                    .collect(Collectors.toSet()));
            set2.addAll(random
                    .ints()
                    .distinct()
                    .limit(190000)
                    .boxed()
                    .collect(Collectors.toSet()));

            Set<Integer> intersection = new HashSet<>(set1);
            intersection.retainAll(set2);
            Set<Integer> union = new HashSet<>(set1);
            union.addAll(set2);
            System.out.printf("Intersection: %d, Union: %d", intersection.size(), union.size());
        };
        TimeCounter timeCounter = new TimeCounter(runnable);
        timeCounter.timeCounter();
    }
}
