public class Interrupt {
    public static void main(String[] args) throws InterruptedException {

Thread t = new Thread(() -> {}); // run()-Methode von Runnable als Lambda-Ausdruck.
t.start();
t.interrupt();
t.join();
System.out.println("Unterbrechungsindikator ist" + (t.isInterrupted() ? "" : " nicht") + " gesetzt.");
// Gibt "Unterbrechungsindikator ist nicht gesetzt." aus.
// Der Indikator wurde von interrupted() zurückgesetzt.

    }
}
