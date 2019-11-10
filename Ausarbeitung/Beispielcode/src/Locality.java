import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Locality {
    static class LocalDataRunnable implements Runnable {
        private static ThreadLocal<Date> dateLocal = ThreadLocal.withInitial(Date::new);
        Date dateShared = new Date();

        @Override
        public void run() {
            System.out.printf("[dateLocal] %s\n", dateLocal.get());
            System.out.printf("[dateShared] %s\n", dateShared);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new LocalDataRunnable()),
                t2 = new Thread(new LocalDataRunnable());
        t1.start();
        t1.join();
        TimeUnit.SECONDS.sleep(2);
        t2.start();
        t2.join();

        /* Ausgabe z.B.:
        [dateLocal] Fri Nov 01 20:00:00 CET 2019
        [dateShared] Fri Nov 01 20:00:00 CET 2019
        [dateLocal] Fri Nov 01 20:00:02 CET 2019
        [dateShared] Fri Nov 01 20:00:00 CET 2019
         */ // [dateShared] bleibt beim ursprünglichen Wert.
    }
}
