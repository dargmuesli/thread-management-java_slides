public class CreatingAndRunning {

static class RunnableThread implements Runnable {
    @Override
    public void run() {
        // Thread-Logik
    }
}

Thread runnableThread = new Thread(new RunnableThread());

static class ExtendedThread extends Thread {
    @Override
    public void run() {
        // Thread-Logik
    }
}

Thread extendedThread = new ExtendedThread();

}
