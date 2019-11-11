import java.util.Date;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadManagement {
  private static ThreadGroup lowPrioTGroup = new ThreadGroup("Low Priority Threads");

  // Ein Runnable, das die Werte seiner Felder ausgibt.
  static class LocalDataRunnable implements Runnable {
    private static ThreadLocal<Date> dateLocal = ThreadLocal.withInitial(Date::new);
    Date dateShared = new Date();

    @Override
    public void run() {
      System.out.printf("[dateLocal] %s\n", dateLocal.get());
      System.out.printf("[dateShared] %s\n", dateShared);
    }
  }

  // Ein endloser Thread, der beim Unterbrechen eine NumberFormatException (unchecked) wirft.
  static class EndlessThread extends Thread {
    @Override
    public void run() {
      while (true) {
        try {
          sleep(1000);
        } catch (InterruptedException e) {
          throw new NumberFormatException();
        }
      }
    }
  }

  // Eine Fabrik für Threads niedriger Priorität.
  static class LowPrioTFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
      Thread newT = new Thread(lowPrioTGroup, r);
      newT.setName("Low Priority Thread");
      newT.setPriority(2);
      return newT;
    }
  }

  // Eine Ausnahmebehandlung, die nur in die Konsole schreibt.
  static class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
      System.out.printf("Look! A %s!\n", e);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread lowPrioLocalDataT = new LowPrioTFactory().newThread(new LocalDataRunnable());
    Thread localDataT = new Thread(new LocalDataRunnable());

    // Allgemeine Infos ausgeben.
    System.out.printf("[lowPriorityFactoryThread] {id: %s, status: %s}\n", lowPrioLocalDataT.getId(), lowPrioLocalDataT.getState());
    System.out.printf("[active threads in low priority thread group]: %d\n", lowPrioTGroup.activeCount());

    // Zweifach, zeitversetzt die Datumswerte aus [LocalDataRunnable] ausgeben.
    lowPrioLocalDataT.start();
    lowPrioLocalDataT.join();
    TimeUnit.SECONDS.sleep(2);
    localDataT.start();
    localDataT.join();
    // Auffällig ist, dass [dateShared] den zuerst gesetzten Wert beibehält.

    /* --- */

    // Endlosen (nicht-Daemon-)Thread starten.
    Thread endlessThread = new EndlessThread();
    endlessThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler());
    endlessThread.start();

    // Endlosen Daemon-Thread starten, der sekündlich überprüft, ob [endlessThread] unterbrochen wurde.
    Thread endlessDaemonThread = new Thread(() -> {
      while (true) {
        try {
          if (endlessThread.isInterrupted()) {
            System.out.println("endlessThread is interrupted.");
          } else {
            System.out.println("endlessThread is not interrupted.");
          }

          Thread.sleep(1000);
        } catch (InterruptedException e) {
          System.out.println("You'll never read this, when this thread is the last thread running.");
          return;
        }
      }
    });
    endlessDaemonThread.setDaemon(true);
    endlessDaemonThread.start();

    TimeUnit.SECONDS.sleep(2);

    // [endlessThread] unterbrechen.
    System.out.println("I'm going to interrupt [endlessThread].");
    endlessThread.interrupt();

    // Die Ausführung endet, sobald nur noch der endlose Daemon-Thread läuft. Es ist sehr unwahrscheinlich, dass "endlessDaemonThread is interrupted" noch ausgegeben wird, weil dieser wahrscheinlich während des Schlafens unterbrochen wird.
  }
}