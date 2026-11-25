# How I built it

## The brief

Build a to-do list on top of a list ADT that I implement myself, then write a report explaining the design and its complexity. I chose a doubly linked list so that showing the list in reverse and removing from the end don't need a walk from the head.

## How it fits together

- **The interface comes first.** `ToDoList` only knows about `ListADT<ToDoItem>`, not about `DoublyLinkedList`. If I wrote an array-based list later, the to-do code wouldn't need to change.
- **Removing needs the most care.** `remove` has four cases: the only node, the head, the tail, and a node in the middle. Each one rewires different pointers. The diagram at the top of `DoublyLinkedList.java` is the picture I worked from.
- **Two kinds of error.** Removing from or reading an empty list throws my own `ListEmptyException`. A bad position throws `IndexOutOfBoundsException`. A blank task name makes `ToDoItem` throw `IllegalArgumentException`, which `ToDoList` catches and turns into a message, so bad input at the menu doesn't crash the app.
- **Duplicates are refused.** `ToDoItem.equals` and `hashCode` compare names, and both add methods check `contains` first. Otherwise "find by name" would only ever return the first match.
- **`getNode` starts from the closer end.** If the index is in the second half of the list, it walks backwards from the tail, so it never walks more than half the list.
- **Tests.** `ToDoTest` is a small runner I wrote with a `check(description, expected, actual)` helper. It tests the to-do operations and the ADT directly, including empty-list and bad-index cases. It's at 43/43.

## The report

The report went through several drafts. The `old/` folder holds a version from before 23 November and one from before the new cover page. The final one went in with the code zip.

## Weak spots and what I'd do next

- **Due dates are plain strings** (`"25/11/2026"`), so the list can't be sorted by date and can't warn you when something is overdue. Switching to `LocalDate` is the first change I'd make.
- **Nothing is saved.** Close the app and the list is gone. Writing it to a file would fix that.
- **Home-made tests.** They work, but JUnit would give proper reports and run in an IDE or CI.
