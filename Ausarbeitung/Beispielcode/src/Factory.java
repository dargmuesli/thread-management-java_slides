import java.util.concurrent.ThreadFactory;

public class Factory {
    public static void main(String[] args) {

// import java.util.concurrent.ThreadFactory;
ThreadFactory threadFactory = r -> null;
Thread t = threadFactory.newThread(() -> {});

if (t == null) {
    System.out.println("Thread ist null.");
}
// Gibt "Thread ist null." aus.

    }
}
