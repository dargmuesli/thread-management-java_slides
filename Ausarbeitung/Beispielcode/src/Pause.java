public class Pause {
    static class LocalDataRunnable implements Runnable {
        @Override
        public void run() {

try {
    Thread.sleep(1000);
    java.util.concurrent.TimeUnit.SECONDS.sleep(1); // java.util.concurrent.TimeUnit kann auch importiert werden.
} catch (InterruptedException e) {
    throw new NumberFormatException();
}

        }
    }
}
