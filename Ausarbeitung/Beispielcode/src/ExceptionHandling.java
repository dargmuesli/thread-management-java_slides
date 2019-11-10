public class ExceptionHandling {
    static class EndlessThread extends Thread {
        @Override
        public void run() {
            try {
                throw new InterruptedException();
            } catch (InterruptedException e) {
                throw new NumberFormatException();
            }
        }
    }

    static class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.printf("Look! A %s!\n", e);
        }
    }

    public static void main(String[] args) {
        Thread endlessThread = new EndlessThread();
        endlessThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler());
        endlessThread.start();
        // Gibt "Look! A java.lang.NumberFormatException!" aus.
    }
}
