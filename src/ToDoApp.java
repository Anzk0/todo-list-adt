import java.util.Scanner;   // only Scanner is imported, for reading keyboard input

/**
 * ToDoApp - a menu for using the ToDo list by hand.
 *
 * ToDoTest runs its checks automatically. This class is a second
 * way of testing, where tasks are typed in and options chosen
 * from a menu.
 *
 * Input is read a whole line at a time, so typing letters where a
 * number is expected gives an error message instead of a crash.
 */
public class ToDoApp {

    private static Scanner input = new Scanner(System.in);
    private static ToDoList list = new ToDoList();

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   COURSEWORK TODO LIST");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readNumber("Choose an option: ");
            System.out.println();

            switch (choice) {
                case 1:  addTask(false);   break;
                case 2:  addTask(true);    break;
                case 3:  removeTask();     break;
                case 4:  findTask();       break;
                case 5:  checkTask();      break;
                case 6:  countTasks();     break;
                case 7:  markDone();       break;
                case 8:  list.display();   break;
                case 9:  list.displayReverse(); break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("  Please choose a number from the menu (0-9).");
            }
        }
        input.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("-------------- MENU --------------");
        System.out.println(" 1. Add a task");
        System.out.println(" 2. Add an URGENT task (goes to the top)");
        System.out.println(" 3. Remove a task by its position");
        System.out.println(" 4. Find a task by its name");
        System.out.println(" 5. Check if a task is on the list");
        System.out.println(" 6. Count the tasks");
        System.out.println(" 7. Mark a task as done");
        System.out.println(" 8. Show the list");
        System.out.println(" 9. Show the list newest first");
        System.out.println(" 0. Exit");
        System.out.println("----------------------------------");
    }

    // Menu options

    private static void addTask(boolean urgent) {
        String name = readText("Task name: ");
        String module = readText("Module: ");
        String dueDate = readText("Due date (e.g. 25/11/2026): ");

        if (urgent) {
            list.addUrgentTodo(name, module, dueDate);
        } else {
            list.addTodo(name, module, dueDate);
        }
    }

    private static void removeTask() {
        if (list.isEmpty()) {
            System.out.println("  There are no tasks to remove.");
            return;
        }
        list.display();
        int position = readNumber("Position of the task to remove: ");
        list.removeTodo(position);   // ToDoList handles invalid positions
    }

    private static void findTask() {
        String name = readText("Name of the task to find: ");
        ToDoItem found = list.findTodo(name);
        if (found == null) {
            System.out.println("  No task called \"" + name + "\" was found.");
        } else {
            System.out.println("  Found at position " + list.findPosition(name) + ": " + found);
        }
    }

    private static void checkTask() {
        String name = readText("Name of the task to check: ");
        if (list.isOnList(name)) {
            System.out.println("  Yes, \"" + name + "\" is on the list.");
        } else {
            System.out.println("  No, \"" + name + "\" is not on the list.");
        }
    }

    private static void countTasks() {
        System.out.println("  You have " + list.countTodos() + " task(s) on your list.");
    }

    private static void markDone() {
        String name = readText("Name of the task you have finished: ");
        list.markCompleted(name);
    }

    // Input helpers

    /**
     * Keeps asking until the user types a whole number.
     * Integer.parseInt throws NumberFormatException for text like "abc",
     * which is caught here instead of crashing the program.
     */
    private static int readNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  \"" + line + "\" is not a number, please try again.");
            }
        }
    }

    /**
     * Reads a line of text from the user.
     */
    private static String readText(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }
}
