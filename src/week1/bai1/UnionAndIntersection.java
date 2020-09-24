package week1.bai1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UnionAndIntersection {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            Random random = new Random();
            Set<Integer> integerSet1 = new HashSet<>();
            random.ints(1, 300001).distinct().limit(200000).forEach(integerSet1::add);
            Set<Integer> integerSet2 = new HashSet<>();
            random.ints(1, 300001).distinct().limit(200000).forEach(integerSet2::add);

            Set<Integer> intersection = new HashSet<>(integerSet1);
            intersection.retainAll(integerSet2);
            intersection.forEach(System.out::println);
            System.out.println("----------------------------------");

            Set<Integer> union = new HashSet<>(integerSet1);
            union.addAll(integerSet2);
            union.forEach(System.out::println);
        };
        TimeCounter timeCounter = new TimeCounter(runnable);
        timeCounter.timeCounter();
    }
}
