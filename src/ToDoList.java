/**
 * ToDoList - the coursework ToDo list.
 *
 * Stores ToDoItem objects in my ListADT (a DoublyLinkedList) and
 * provides add, count, remove by position, find by name and check
 * if a task is on the list.
 *
 * Positions shown to the user start at 1, so they are converted to
 * the list's 0-based indexes here.
 *
 * Exceptions from the list are caught in this class and turned into
 * a message for the user.
 */
public class ToDoList {

    // Declared as the interface type, so the implementation could be swapped
    private ListADT<ToDoItem> tasks;

    /**
     * Creates an empty ToDo list.
     */
    public ToDoList() {
        this.tasks = new DoublyLinkedList<>();
    }

    // 1. ADD

    /**
     * Adds a task to the end of the list.
     * Duplicate names are refused so every task can be found by name.
     *
     * @return true if added, false if it was a duplicate or invalid
     */
    public boolean addTodo(String name, String module, String dueDate) {
        try {
            ToDoItem item = new ToDoItem(name, module, dueDate);
            if (tasks.contains(item)) {
                System.out.println("  Not added: \"" + item.getName() + "\" is already on the list.");
                return false;
            }
            tasks.add(item);
            System.out.println("  Added: " + item);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("  Not added: " + e.getMessage());
            return false;
        }
    }

    /**
     * Adds an urgent task to the FRONT of the list so it is seen first.
     *
     * @return true if added, false if it was a duplicate or invalid
     */
    public boolean addUrgentTodo(String name, String module, String dueDate) {
        try {
            ToDoItem item = new ToDoItem(name, module, dueDate);
            if (tasks.contains(item)) {
                System.out.println("  Not added: \"" + item.getName() + "\" is already on the list.");
                return false;
            }
            tasks.addFirst(item);
            System.out.println("  Added URGENT task to the top: " + item);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("  Not added: " + e.getMessage());
            return false;
        }
    }

    // 2. COUNT

    /**
     * @return how many tasks are on the list
     */
    public int countTodos() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    // 3. REMOVE BY POSITION

    /**
     * Removes the task at the given position (1 = first task).
     *
     * @param position the task's position as shown in the list
     * @return the removed task, or null if it could not be removed
     */
    public ToDoItem removeTodo(int position) {
        try {
            ToDoItem removed = tasks.remove(position - 1); // convert to 0-based
            System.out.println("  Removed task " + position + ": " + removed);
            return removed;
        } catch (ListEmptyException e) {
            System.out.println("  Cannot remove: there are no tasks on the list.");
            return null;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Cannot remove: there is no task at position " + position
                + " (the list has " + tasks.size() + " task(s)).");
            return null;
        }
    }

    // 4. FIND BY NAME

    /**
     * Finds a task by its name (not case sensitive).
     *
     * @param name the name to search for
     * @return the matching task, or null if there is no match
     */
    public ToDoItem findTodo(String name) {
        int position = findPosition(name);
        if (position == -1) {
            return null;
        }
        return tasks.get(position - 1);
    }

    /**
     * Finds the position of a task by its name.
     *
     * @param name the name to search for
     * @return the position (1 = first), or -1 if not found
     */
    public int findPosition(String name) {
        if (name == null || name.trim().isEmpty()) {
            return -1;
        }
        int index = tasks.indexOf(new ToDoItem(name)); // name-only search key
        if (index == -1) {
            return -1;
        }
        return index + 1; // convert to 1-based
    }

    // 5. CHECK IF ON THE LIST

    /**
     * @param name the task name to check
     * @return true if a task with this name is on the list
     */
    public boolean isOnList(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return tasks.contains(new ToDoItem(name));
    }

    // EXTRA - mark a task as done

    /**
     * Ticks off a task by name.
     *
     * @return true if the task was found and marked, false otherwise
     */
    public boolean markCompleted(String name) {
        ToDoItem item = findTodo(name);
        if (item == null) {
            System.out.println("  Cannot mark as done: \"" + name + "\" is not on the list.");
            return false;
        }
        item.markCompleted();
        System.out.println("  Marked as done: " + item);
        return true;
    }

    // DISPLAY

    /**
     * Prints every task with its position, first to last.
     * Note: each get(i) walks the list, so this is O(n^2) overall.
     * That is fine for a short personal ToDo list, but for a very long
     * list an iterator over the nodes would be better (see report).
     */
    public void display() {
        System.out.println("  --- ToDo list (" + countTodos() + " task(s)) ---");
        if (isEmpty()) {
            System.out.println("  (no tasks)");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints the tasks last to first, e.g. to see the most recently
     * added tasks first. Positions are counted down from the end.
     * get() starts from the tail for the second half of the list, so
     * this walks backwards through the prev links.
     */
    public void displayReverse() {
        System.out.println("  --- ToDo list, newest first ---");
        if (isEmpty()) {
            System.out.println("  (no tasks)");
            return;
        }
        for (int i = tasks.size() - 1; i >= 0; i--) {
            System.out.println("  " + (i + 1) + ". " + tasks.get(i));
        }
    }
}
