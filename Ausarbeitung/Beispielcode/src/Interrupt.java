public class Interrupt {
    public static void main(String[] args) {

Thread t = new Thread(() -> {
    // Endlose Schleife bis zum Unterbrechen.
    while (true) {
        if (Thread.interrupted()) { // Kürzer als Thread.currentThread().isInterrupted()
            return;
        }
    }
});
t.interrupt();
System.out.println("Unterbrechungsindikator ist" + (t.isInterrupted() ? "" : " nicht") + " gesetzt.");
// Gibt "Unterbrechungsindikator ist nicht gesetzt." aus.
// Der Indikator wurde von interrupted() zurückgesetzt.

    }
}
