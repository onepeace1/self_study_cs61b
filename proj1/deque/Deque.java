package deque;

import java.util.Iterator;

public interface Deque<T> {

    /** Adds an item to the front of the list. */
    public void addFirst(T x);

    /** Adds an item to the end of the list. */
    public void addLast(T x);

    /** Returns the number of items in the list using recursion. */
    public int size();

    /**Returns true if deque is empty, false otherwise.**/
    public boolean isEmpty();

    /**Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.**/
    public void printDeque();

    public T removeFirst();

    public T removeLast();

    public T get(int index);

    public Iterator<T> iterator();

    public boolean equals(Object o);

}
