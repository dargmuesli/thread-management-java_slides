public class Properties {
    public static void main(String[] args) throws InterruptedException {

Thread t = new Thread(() -> {
    // Endlose Schleife bis zum Unterbrechen.
    while (true) {
        if (Thread.interrupted()) {
            return;
        }
    }
});
System.out.printf("Id: %s\nName: %s\nPrioritaet: %s\nStatus: %s\n\n",
        t.getId(), t.getName(), t.getPriority(), t.getState());

t.setName("Calculator");
t.setPriority(1);
t.start();
System.out.printf("Name: %s\nPrioritaet: %s\nStatus: %s\n\n",
        t.getName(), t.getPriority(), t.getState());

t.interrupt();
t.join();
System.out.printf("Status: %s\n\n", t.getState());

/* Ausgabe:
Id: 11
Name: Thread-0
Prioritaet: 5
Status: NEW

Name: Calculator
Prioritaet: 1
Status: RUNNABLE

Status: TERMINATED
*/

    }
}
