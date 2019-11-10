public class Daemon {

public static void main(String[] args) {
    Thread daemon = new Thread(() -> {
        while (true) {}
    });
    daemon.setDaemon(true);
    daemon.start();
    // Dieses Programm endet hier trotz der Endlosschleife im Daemon-Thread.
}

}
