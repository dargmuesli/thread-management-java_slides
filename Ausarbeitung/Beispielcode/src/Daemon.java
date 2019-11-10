public class Daemon {

public static void main(String[] args) {
    Thread dt = new Thread(() -> {
        while (true) {}
    });
    dt.setDaemon(true);
    dt.start();
    // Dieses Programm endet hier trotz der Endlosschleife im Daemon-Thread.
}

}
