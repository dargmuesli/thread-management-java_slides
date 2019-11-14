public class Wait {

public static void main(String[] args) throws InterruptedException {
    Thread t = new Thread(() -> {
        // irgendeine Berechnung
    });
    t.start();
    t.join(); // Wartet auf Beendigung des Threads [t]. Kann eine InterruptedException auslösen. Siehe Methodensignatur oben.
    // Ab dieser Zeile ist der Thread mit Sicherheit beendet.
}

}
