import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.RunnableFuture;

public class AltMenu {
    String title;
    Runnable action;

    public AltMenu(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return this.title;
    }

    public Runnable getAction() {
        return this.action;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Integer, AltMenu> menu = new HashMap<>(); // using Map

        menu.put(1, new AltMenu("Swapper", () -> IntByReference.main(null) ) );
        menu.put(2, new AltMenu("Matrix", () -> Matrix.main(null) ) );

        System.out.println("Menu:");
        for (Map.Entry<Integer, AltMenu> pair : menu.entrySet()) {
            System.out.println(pair.getKey() + " ==> " + pair.getValue().getTitle());
        }

        int input = sc.nextInt();
        AltMenu m = menu.get(input);
        m.getAction().run();

        AltMenu.main(args);
    }
}