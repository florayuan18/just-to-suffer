import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AltMenu {
    private String title; // numerical
    private Runnable action; // changed class variable to be private

    // constructor
    public AltMenu(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    // getter 1 - return the value
    public String getTitle() {
        return this.title;
    }

    // getter 2 - return the value
    public Runnable getAction() {
        return this.action;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Integer, AltMenu> menu = new HashMap<>(); // like array but each element is composed on key and value pair to store information
        // key makes data unique, no two entries with same key

        // menu.put is creating a new element,
        menu.put(1, new AltMenu("Swapper", () -> IntByReference.main(null) ) ); // options for the user to pick from
        menu.put(2, new AltMenu("Matrix", () -> Matrix.main(null) ) ); // 2 is key, making a new class object
        menu.put(3, new AltMenu("Queue Iterator", () -> QueueTester.main(null) ) );
        menu.put(4, new AltMenu("Merge Queues", () -> QueueMerge.main(null) ) );
        menu.put(5, new AltMenu("Reverse Queues", () -> QueueReverse.main(null) ) );
        menu.put(6, new AltMenu("Calculator", () -> Calculator.main(null) ) );

        System.out.println("Menu:");
        // Map.Entry - access entries within a map
        for (Map.Entry<Integer, AltMenu> pair : menu.entrySet()) { // entrySet returns a set that has the same elements as the map, entrySet is an array, take elements out
            System.out.println(pair.getKey() + " ==> " + pair.getValue().getTitle()); // printing out key and value combination
        }

        int input = sc.nextInt();
        AltMenu m = menu.get(input); // giving the input to menu.get - getting the action?
        m.getAction().run(); // running the action

        AltMenu.main(args); // Recursively run Menu again
    }
}