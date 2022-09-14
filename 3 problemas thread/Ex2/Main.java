import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<ThreadFruit> threadFruits = new ArrayList<>();

    public static void runThreads() throws InterruptedException {
        threadFruits.forEach(Thread::start);

        Thread.sleep(3000);

        threadFruits.forEach(Thread::interrupt);

        System.out.println(threadFruits.size() + " threads stopped");
    }

    public static void addNewThread(String fruitName) {
        threadFruits.add(new ThreadFruit(fruitName));
    }

    public static void main(String[] args) throws InterruptedException {
        addNewThread("Maçã");
        addNewThread("Banana");
        addNewThread("Pera");
        addNewThread("Abacate");
        addNewThread("Kiwi");

        runThreads();
    }

}