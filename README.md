# ToDo list ADT

A command-line to-do list for the Year 2 Data Structures module at the University of Salford. The tasks are stored in a doubly linked list that I wrote myself. It doesn't use `java.util` collections at all.

## What it does

- Add a task to the end of the list, or add an urgent task to the front
- Remove a task by its position
- Find a task by name, check whether it's on the list, and count the tasks
- Mark a task as done
- Print the list forwards or backwards

Each task has a name, a module and a due date. Duplicate names are refused so every task can be found by name.

## How it's built

| File | What it does |
|---|---|
| `ListADT.java` | The interface: `add`, `addFirst`, `remove`, `get`, `size`, `isEmpty`, `indexOf`, `contains` |
| `Node.java` | One node with `data`, `next` and `prev` |
| `DoublyLinkedList.java` | My implementation of `ListADT` using head, tail and size |
| `ListEmptyException.java` | Thrown when you remove or get from an empty list |
| `ToDoItem.java` | A single task, with `equals` and `hashCode` based on the name |
| `ToDoList.java` | The to-do operations, built on top of the ADT |
| `ToDoApp.java` | Menu-driven console app |
| `ToDoTest.java` | My own test runner (43 checks) |

Keeping a `tail` pointer makes adding to the end O(1), and the `size` field makes counting O(1). `getNode` starts from whichever end is closer to the index, so it never walks more than half the list.

## Running it

```bash
cd src
javac *.java
java ToDoApp     # the menu app
java ToDoTest    # the tests
```

The tests currently pass 43/43.

See [PROCESS.md](PROCESS.md) for how I built it and what went wrong along the way.
