import java.util.concurrent.TimeUnit;

public class Pause {
    static class LocalDataRunnable implements Runnable {
        @Override
        public void run() {

// import java.util.concurrent.TimeUnit;
try {
    Thread.sleep(1000);
    TimeUnit.SECONDS.sleep(1);
} catch (InterruptedException e) {
    // ...
}

        }
    }
}
