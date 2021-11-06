package ods;

public interface List<T> {

    // The following seven list and deque operations shouldn't need description
    boolean add(int i, T x);
    void set(int i, T x);
    T remove(int i);

    boolean addFront(T x);
    boolean addBack(T x);

    T removeFront();
    T removeBack();

    // The fundamental set operation. For simplicity, use .equals() for comparison
    // instead of a comparator.
    T find(T x);

    // bookkeeping
    boolean isEmpty();
    int size();

    // empty the list.
    void clear();

    // A few of less common operations. In all cases, if idx is not a legal index,
    // throw an IndexOutOfBounds exception.

    // return a list of elements from index 0 up to idx-1. This list should now
    // only contain the remaining elements (from idx forward).
    List<T> split(int idx);

    // move the entry at index idx to the front of the list
    void promote(int idx);

    // The "tail" of this list (that is, the elements from position idx to the
    // end) should be swapped with the (entire) contents of other. So, after this
    // is called, other should contain the tail of this list, and the elements of
    // this list from position idx forward should be the original contents of other.
    void swapTail(int idx, List<T> other);

}
