public class Interrupt {
    public static void main(String[] args) {

Thread calc = new Thread(() -> {
    // Endlose Schleife bis zum Unterbrechen.
    while (true) {
        if (Thread.interrupted()) {
            return;
        }
    }
});
calc.interrupt();
System.out.println("Unterbrechungsindikator ist" + (calc.isInterrupted() ? "" : " nicht") + " gesetzt.");
// Gibt "Unterbrechungsindikator ist nicht gesetzt." aus.
// Der Indikator wurde von interrupted() zurückgesetzt.

    }
}
