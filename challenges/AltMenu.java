import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class AltMenu {
    Map<Integer, MenuRow> menu = new HashMap<>();

    public AltMenu(MenuRow[] rows) {
        int i = 0;
        for (MenuRow row : rows) {
            // Build HashMap for lookup convenience
            menu.put(i++, new MenuRow(row.getTitle(), row.getAction()));
        }
    }
    public MenuRow get(int i) {
        return menu.get(i);
    }

    public void print() {
        for (Map.Entry<Integer, MenuRow> pair : menu.entrySet()) {
            System.out.println(pair.getKey() + " :  " + pair.getValue().getTitle());
        }
    }

    public static void main(String[] args) {
        Driver.main(args);
    }

}

// The MenuRow Class has title and action for individual line item in menu
class MenuRow {
    String title;
    Runnable action;

    public MenuRow(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return this.title;
    }
    public Runnable getAction() {
        return this.action;
    }

    public void run() {
        action.run();
    }

}

class Driver {
    public static void runMenu(AltMenu current){
        while (true) {

            System.out.println("Choose:");
            // print rows
            current.print();

            // Scan for input
            try {
                Scanner sc = new Scanner(System.in);
                int selection = sc.nextInt();

                // menu action
                try {
                    MenuRow row = current.get(selection);
                    // stop menu condition
                    if (row.getTitle().equals("Exit"))
                        return;
                    // run option
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(row.getTitle());
                    row.run();
                } catch (Exception e) {
                    System.out.printf("Invalid selection %d\n", selection);
                }
            } catch (Exception e) {
                System.out.println("Not a number");
            }
        }
    }


    public static void main(String[] args) {
        // Row initialize
        MenuRow[] rows = new MenuRow[]{
                new MenuRow("Exit", () -> main(null)),
                new MenuRow("Swapper", () -> IntByReference.main(null)),
                new MenuRow("Matrix", () -> Matrix.main(null))
        };

        MenuRow[] rows1 = new MenuRow[]{
                new MenuRow("Exit", () -> main(null)),
                new MenuRow("Queue Iterator", () -> QueueTester.main(null)),
                new MenuRow("Merge Queues", () -> QueueMerge.main(null)),
                new MenuRow("Reverse Queues", () -> QueueReverse.main(null))
        };

        MenuRow[] rows2 = new MenuRow[]{
                new MenuRow("Exit", () -> main(null)),
                new MenuRow("Calculator", () -> Calculator.main(null))
        };

        MenuRow[] rows3 = new MenuRow[]{
                new MenuRow("Exit", () -> main(null)),
                new MenuRow("Bubble Sort", () -> BubbleSort.main(null)),
                new MenuRow("Insertion Sort", () -> InsertionSort.main(null)),
                new MenuRow("Merge Sort", () -> MergeSort.main(null)),
                new MenuRow("Selection Sort", () -> SelectionSort.main(null))
        };


        // Menu construction
        AltMenu menu0 = new AltMenu(rows);
        AltMenu menu1 = new AltMenu (rows1);
        AltMenu menu2 = new AltMenu(rows2);
        AltMenu menu3 = new AltMenu(rows3);

        MenuRow[] weeks = new MenuRow[]{
                new MenuRow("Week 0", () -> runMenu(menu0)),
                new MenuRow("Week 1", () -> runMenu(menu1)),
                new MenuRow("Week 2", () -> runMenu(menu2)),
                new MenuRow("Week 3", () -> runMenu(menu3))
        };

        AltMenu weekmenu = new AltMenu(weeks);

        runMenu(weekmenu);
    }
}