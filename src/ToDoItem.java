/**
 * ToDoItem - one task on the coursework ToDo list.
 *
 * Each task has a name, the module it belongs to, a due date and
 * a completed flag.
 *
 * Two tasks count as equal if their names match, ignoring case.
 * That is how indexOf() and contains() in the list can find a task
 * by its name.
 */
public class ToDoItem {

    private String name;
    private String module;
    private String dueDate;   // stored as text, e.g. "25/11/2026"
    private boolean completed;

    /**
     * Creates a new, not yet completed, task.
     *
     * @param name    what needs doing (must not be blank)
     * @param module  which module it is for
     * @param dueDate when it is due
     * @throws IllegalArgumentException if the name is null or blank
     */
    public ToDoItem(String name, String module, String dueDate) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A task must have a name.");
        }
        this.name = name.trim();
        this.module = module;
        this.dueDate = dueDate;
        this.completed = false;
    }

    /**
     * Creates a task with only a name. Used as a "search key" when
     * looking for a task by its name, since equals() only compares names.
     *
     * @param name the task name to search for
     */
    public ToDoItem(String name) {
        this(name, "", "");
    }

    public String getName() {
        return name;
    }

    public String getModule() {
        return module;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Two tasks are equal if their names match, ignoring case,
     * so "DS Report" and "ds report" count as the same task.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToDoItem)) {
            return false;
        }
        ToDoItem otherItem = (ToDoItem) other;
        return this.name.equalsIgnoreCase(otherItem.name);
    }

    /**
     * Java rule: if equals() is overridden, hashCode() must be too,
     * so equal objects give the same hash code.
     */
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    /**
     * e.g. "[ ] DS Report (Data Structures, due 25/11/2026)"
     *      "[X] ..." once completed
     */
    @Override
    public String toString() {
        String tick = completed ? "[X] " : "[ ] ";
        return tick + name + " (" + module + ", due " + dueDate + ")";
    }
}
