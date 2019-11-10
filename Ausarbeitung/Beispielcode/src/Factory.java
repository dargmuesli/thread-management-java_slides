public class Factory {
    public static void main(String[] args) {

java.util.concurrent.ThreadFactory threadFactory = r -> null; // java.util.concurrent.ThreadFactory kann auch importiert werden.
Thread t = threadFactory.newThread(() -> {});

if (t == null) {
    System.out.println("Thread ist null.");
}

// Gibt "Thread ist null." aus.

    }
}
