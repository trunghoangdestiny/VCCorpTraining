package week1.bai1;

public class TimeCounter {
    Runnable runnable;

    public TimeCounter(Runnable runnable) {
        this.runnable = runnable;
    }

    public void timeCounter() {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        System.out.printf("Time running on %s: %d ms", runnable.hashCode(), end - start);
    }
}
