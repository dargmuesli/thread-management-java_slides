public class Wait {

public static void main(String[] args) throws InterruptedException {
    Thread t = new Thread(() -> {
        // irgendeine Berechnung
    });
    t.start();
    t.join(1000); // Wartet maximal eine Sekunde auf Beendigung des Threads [t]. Kann eine InterruptedException auslösen. Siehe Methodensignatur.
    // Ab dieser Zeile ist der Thread mit Sicherheit beendet.
}

}
