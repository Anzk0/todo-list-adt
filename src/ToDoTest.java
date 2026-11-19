/**
 * ToDoTest - tests every feature of the ToDo list.
 *
 * Each test prints what it is checking, the expected and actual result,
 * and PASS or FAIL. A summary is printed at the end.
 * The output of this class is used for the screenshots in the report.
 */
public class ToDoTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("=====================================================");
        System.out.println("   COURSEWORK TODO LIST - TEST RUN");
        System.out.println("=====================================================");

        ToDoList list = new ToDoList();

        heading("TEST 1: Start with an empty list");
        check("New list is empty", true, list.isEmpty());
        check("New list has 0 tasks", 0, list.countTodos());
        list.display();

        heading("TEST 2: Add 6 tasks");
        list.addTodo("DS ToDo ADT code", "Data Structures", "25/11/2026");
        list.addTodo("DS report", "Data Structures", "25/11/2026");
        list.addTodo("NPT recon report", "Network Penetration Testing", "26/11/2026");
        list.addTodo("Revise subnetting", "Network and Security", "01/12/2026");
        list.addTodo("Client server lab 6", "Client Server Systems", "03/12/2026");
        list.addTodo("Read lecture notes week 9", "Data Structures", "05/12/2026");
        list.display();
        check("Count after adding 6 tasks", 6, list.countTodos());
        check("List is no longer empty", false, list.isEmpty());

        heading("TEST 3: Add an urgent task to the front");
        list.addUrgentTodo("Book meeting with tutor", "Personal Tutor", "20/11/2026");
        list.display();
        check("Urgent task is at position 1", 1, list.findPosition("Book meeting with tutor"));
        check("Count is now 7", 7, list.countTodos());

        heading("TEST 4: Adding invalid tasks is refused");
        check("Duplicate name is refused", false,
              list.addTodo("ds REPORT", "Data Structures", "25/11/2026"));
        check("Blank name is refused", false, list.addTodo("   ", "Data Structures", "25/11/2026"));
        check("Null name is refused", false, list.addTodo(null, "Data Structures", "25/11/2026"));
        check("Count is still 7", 7, list.countTodos());

        heading("TEST 5: Count the tasks");
        System.out.println("  countTodos() returned: " + list.countTodos());
        check("countTodos() gives 7", 7, list.countTodos());

        heading("TEST 6: Find a task by its name");
        ToDoItem found = list.findTodo("NPT recon report");
        System.out.println("  Found: " + found);
        check("Found task is not null", true, found != null);
        check("Found the right module", "Network Penetration Testing",
              found == null ? null : found.getModule());
        check("Position of 'NPT recon report'", 4, list.findPosition("NPT recon report"));
        check("Search ignores upper/lower case", true, list.findTodo("npt RECON report") != null);
        check("Missing task returns null", true, list.findTodo("Learn Python") == null);
        check("Missing task position is -1", -1, list.findPosition("Learn Python"));

        heading("TEST 7: Check if a task is on the list");
        check("'DS report' is on the list", true, list.isOnList("DS report"));
        check("'Revise subnetting' is on the list", true, list.isOnList("Revise subnetting"));
        check("'Learn Python' is NOT on the list", false, list.isOnList("Learn Python"));
        check("Blank name is NOT on the list", false, list.isOnList(""));

        heading("TEST 8: Mark a task as completed");
        check("Mark 'DS ToDo ADT code' done", true, list.markCompleted("DS ToDo ADT code"));
        check("Task now shows as completed", true, list.findTodo("DS ToDo ADT code").isCompleted());
        check("Cannot mark a missing task", false, list.markCompleted("Learn Python"));
        list.display();

        heading("TEST 9: Remove tasks by position (middle, first, last)");
        ToDoItem removed = list.removeTodo(4);  // middle
        check("Removed the middle task (position 4)", "NPT recon report",
              removed == null ? null : removed.getName());
        check("It is no longer on the list", false, list.isOnList("NPT recon report"));

        removed = list.removeTodo(1);  // first (head)
        check("Removed the first task", "Book meeting with tutor",
              removed == null ? null : removed.getName());

        removed = list.removeTodo(list.countTodos());  // last (tail)
        check("Removed the last task", "Read lecture notes week 9",
              removed == null ? null : removed.getName());

        check("Count is now 4", 4, list.countTodos());
        list.display();

        heading("TEST 10: Display the list in reverse (uses prev links)");
        list.displayReverse();

        heading("TEST 11: Removing an invalid position is handled");
        check("Position 0 is refused", true, list.removeTodo(0) == null);
        check("Position 99 is refused", true, list.removeTodo(99) == null);
        check("Negative position is refused", true, list.removeTodo(-3) == null);
        check("Count is still 4", 4, list.countTodos());

        heading("TEST 12: Empty the list, then try to remove");
        while (!list.isEmpty()) {
            list.removeTodo(1);
        }
        check("List is empty again", true, list.isEmpty());
        check("Count is 0", 0, list.countTodos());
        check("Removing from empty list is handled", true, list.removeTodo(1) == null);
        list.display();

        heading("TEST 13: Test the DoublyLinkedList ADT directly");
        testAdtDirectly();

        System.out.println();
        System.out.println("=====================================================");
        System.out.println("   RESULTS: " + passed + " passed, " + failed + " failed, "
            + (passed + failed) + " total");
        System.out.println("=====================================================");
    }

    /**
     * Tests the list on its own with Integers, to show the ADT is
     * generic and that its exceptions are thrown correctly.
     */
    private static void testAdtDirectly() {
        ListADT<Integer> numbers = new DoublyLinkedList<>();
        numbers.add(20);
        numbers.add(30);
        numbers.addFirst(10);
        System.out.println("  Forwards: " + numbers + "   Reverse: " + numbers.toStringReverse());
        check("Integer list forwards", "[10, 20, 30]", numbers.toString());
        check("Integer list reverse", "[30, 20, 10]", numbers.toStringReverse());
        check("isValidIndex(2) is true", true, numbers.isValidIndex(2));
        check("isValidIndex(3) is false", false, numbers.isValidIndex(3));

        try {
            numbers.get(5);
            check("get(5) throws IndexOutOfBoundsException", true, false);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Caught: " + e.getMessage());
            check("get(5) throws IndexOutOfBoundsException", true, true);
        }

        ListADT<Integer> empty = new DoublyLinkedList<>();
        try {
            empty.remove(0);
            check("remove() on empty list throws ListEmptyException", true, false);
        } catch (ListEmptyException e) {
            System.out.println("  Caught: " + e.getMessage());
            check("remove() on empty list throws ListEmptyException", true, true);
        }

        try {
            numbers.add(null);
            check("add(null) throws IllegalArgumentException", true, false);
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught: " + e.getMessage());
            check("add(null) throws IllegalArgumentException", true, true);
        }
    }

    // Small helper methods for printing test results

    private static void heading(String title) {
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println(title);
        System.out.println("-----------------------------------------------------");
    }

    /**
     * Compares expected and actual values and prints PASS or FAIL.
     * Object is used so it works for ints, booleans and Strings.
     */
    private static void check(String description, Object expected, Object actual) {
        boolean ok = (expected == null) ? actual == null : expected.equals(actual);
        if (ok) {
            passed++;
            System.out.println("  PASS - " + description);
        } else {
            failed++;
            System.out.println("  FAIL - " + description + " (expected: " + expected
                + ", actual: " + actual + ")");
        }
    }
}
