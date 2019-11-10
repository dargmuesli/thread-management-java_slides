public class Wait {

public static void main(String[] args) throws InterruptedException {
    Thread calc = new Thread(() -> {
        // irgendeine Berechnung
    });
    calc.start();
    calc.join(); // Kann eine InterruptedException auslösen. Siehe Methodensignatur.
    // Ab dieser Zeile ist die Berechnung mit Sicherheit abgeschlossen.
}

}
