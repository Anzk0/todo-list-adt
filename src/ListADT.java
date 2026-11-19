/**
 * ListADT - my Abstract Data Type for a list.
 *
 * The interface says what a list can do, and DoublyLinkedList
 * decides how. Because ToDoList only uses this interface, the list
 * underneath could be swapped without changing ToDoList.
 *
 * It is generic (<T>), so it can store any type, e.g. ToDoItem
 * or Integer.
 *
 * Positions (indexes) start at 0, like Java arrays.
 *
 * @param <T> the type of element stored in the list
 */
public interface ListADT<T> {

    /**
     * Adds an item to the END of the list.
     *
     * @param item the item to add (must not be null)
     * @throws IllegalArgumentException if item is null
     */
    void add(T item);

    /**
     * Adds an item to the FRONT of the list.
     *
     * @param item the item to add (must not be null)
     * @throws IllegalArgumentException if item is null
     */
    void addFirst(T item);

    /**
     * Removes the item at the given position and returns it.
     * All items after it move up one position.
     *
     * @param index position of the item to remove (0 to size-1)
     * @return the item that was removed
     * @throws ListEmptyException if the list has no items
     * @throws IndexOutOfBoundsException if the index is not valid
     */
    T remove(int index) throws ListEmptyException;

    /**
     * Returns the item at the given position without removing it.
     *
     * @param index position of the item (0 to size-1)
     * @return the item at that position
     * @throws ListEmptyException if the list has no items
     * @throws IndexOutOfBoundsException if the index is not valid
     */
    T get(int index) throws ListEmptyException;

    /**
     * Counts how many items are currently in the list.
     *
     * @return the number of items
     */
    int size();

    /**
     * Checks whether the list has no items in it.
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Searches the list for an item and returns its position.
     * Items are compared using their equals() method.
     *
     * @param item the item to search for
     * @return the position of the first match, or -1 if not found
     */
    int indexOf(T item);

    /**
     * Checks whether an item is in the list.
     *
     * @param item the item to look for
     * @return true if the item is in the list, false otherwise
     */
    boolean contains(T item);

    /**
     * Checks whether a position is valid for the current list,
     * i.e. it is between 0 and size-1.
     *
     * @param index the position to check
     * @return true if the index is valid, false otherwise
     */
    boolean isValidIndex(int index);

    /**
     * Builds a String of every item from FIRST to LAST.
     *
     * @return the list contents in normal order
     */
    String toString();

    /**
     * Builds a String of every item from LAST to FIRST.
     * This uses the backward (prev) links of the doubly linked list.
     *
     * @return the list contents in reverse order
     */
    String toStringReverse();
}
