package week2.util;

public class TimeCounter {
    Runnable runnable;

    public TimeCounter(Runnable runnable) {
        this.runnable = runnable;
    }

    public long count() {
        long start = System.currentTimeMillis();
        this.runnable.run();
        long end = System.currentTimeMillis();
        System.out.printf("Total time %s: %s ms\n", runnable.hashCode(), (end - start));
        return end - start;
    }
}
