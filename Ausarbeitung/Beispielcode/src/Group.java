public class Group {
    public static void main(String[] args) {

ThreadGroup parentGroup = new ThreadGroup("Parent");
new ThreadGroup(parentGroup, "Child");
new Thread(parentGroup, () -> {});

    }
}
