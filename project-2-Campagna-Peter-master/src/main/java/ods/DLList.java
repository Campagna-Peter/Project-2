package ods;

public class DLList<T> implements List<T> {

    class Node {
        T data;
        Node prev, next;
    }

    private int size;
    Node dummy;

    public DLList() {
        dummy = new Node();
        dummy.next = dummy;
        dummy.prev = dummy;
        size = 0;
    }

    @Override
    public boolean add(int i, T x) {
        Node tempNode = new Node();
        tempNode.data = x;
        if (i < 0 || i > size - 1)
            throw new IndexOutOfBoundsException();
        Node p = null;
        if (i < size / 2) {
            p = dummy.next;
            for (int j = 0; j < i; j++)
                p = p.next;
        } else {
            p = dummy;
            for (int j = size; j > i; j--)
                p = p.prev;
        }

        tempNode.prev = p.prev;
        p.prev.next = tempNode;
        p.prev = tempNode;
        tempNode.next = p;
        size++;
        return true;
    }

    @Override
    public void set(int i, T x) {
        if (i < 0 || i > size - 1)
            throw new IndexOutOfBoundsException();
        Node tempNode = null;
        if (i < size / 2) {
            tempNode = dummy.next;
            for (int j = 0; j < i; j++)
                tempNode = tempNode.next;
        } else {
            tempNode = dummy;
            for (int j = size; j > i; j--)
                tempNode = tempNode.prev;
        }

        tempNode.data = x;

    }

    @Override
    public T remove(int i) {
        if (i < 0 || i > size - 1)
            throw new IndexOutOfBoundsException();
        Node p = null;
        if (i < size / 2) {
            p = dummy.next;
            for (int j = 0; j < i; j++)
                p = p.next;
        } else {
            p = dummy;
            for (int j = size; j > i; j--)
                p = p.prev;
        }

        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
        return p.data;
    }

    @Override
    public boolean addFront(T x) {
        Node tempNode = new Node();
        tempNode.data = x;

        tempNode.next = dummy.next;
        dummy.next = tempNode;
        tempNode.prev = dummy;
        tempNode.next.prev = tempNode;

        size++;
        return true;

    }

    @Override
    public boolean addBack(T x) {
        Node tempNode = new Node();
        tempNode.data = x;

        tempNode.prev = dummy.prev;
        dummy.prev = tempNode;
        tempNode.next = dummy;
        tempNode.prev.next = tempNode;

        size++;
        return true;
    }

    @Override
    public T removeFront() {
        Node tempNode = new Node();

        tempNode = dummy.next;
        dummy.next = tempNode.next;

        size--;
        return dummy.next.data;
    }

    @Override
    public T removeBack() {

        Node tempNode = new Node();

        tempNode = dummy.prev;
        tempNode.prev.next = dummy;
        dummy.prev = tempNode.prev;

        size--;
        return dummy.prev.data;
    }

    @Override
    public T find(T x) {
        Node tempNode = dummy.next;
        for (int i = 0; i < size; i++) {
            if (tempNode.data.equals(x)) {
                return x;
            }
            tempNode = tempNode.next;
        }
        return tempNode.data;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        dummy.next = dummy;
        dummy.prev = dummy;
        size = 0;

    }

    @Override
    // This would be O(n) because it is iterating up to the point where it splits.
    // Other than that it is O(1) because it is reassigning references.
    public List<T> split(int idx) {
        DLList<T> list = new DLList<>();
        Node tempNode = dummy.next;
        if (idx < 0 || idx > size - 1)
            throw new IndexOutOfBoundsException();
        for (int j = 0; j < idx; j++) {
            dummy.next = tempNode.next;
            tempNode.next.prev = dummy;
            list.addBack(tempNode.data);
            tempNode = tempNode.next;
            size--;
            list.size++;
        }

        return list;
    }

    @Override
    // This would be O(n) because it has to iterate over the list and then reassigns
    // a reference, which is O(1), but I still had to iterate up to the node I was
    // promoting. Therefore, it is O(n).
    public void promote(int idx) {
        Node tempNode = dummy;
        if (idx < 0 || idx > size - 1)
            throw new IndexOutOfBoundsException();
        Node p = null;
        if (idx < size / 2) {
            p = dummy.next;
            for (int j = 0; j < idx; j++)
                p = p.next;
        } else {
            p = dummy;
            for (int j = size; j > idx; j--)
                p = p.prev;
        }

        T promoted = remove(idx);
        addFront(promoted);

    }

    @Override
    // This is O(n) because it iterates up to the point of splitting other than that
    // it is O(1) becasue it is reassinging references.
    // If it is not a DLList it is stll O(n) but it is much slower because you have
    // to add and remove each element individually.
    public void swapTail(int idx, List<T> other) {
        if (other instanceof DLList<?>) {
            DLList<T> nother = (DLList<T>) other;

            Node cur = dummy;
            if (idx < 0 || idx > size)
                throw new IndexOutOfBoundsException();
            for (int j = 0; j < idx; j++) {
                cur = cur.next;
            }
            Node tempNode = cur.next;
            Node tempNode2 = new Node();
            tempNode2.next = dummy.prev;
            cur.next = nother.dummy.next;
            nother.dummy.next.prev = cur;
            tempNode.prev = nother.dummy;
            nother.dummy.next = tempNode;
            dummy.prev.next = nother.dummy;
            nother.dummy.prev.next = dummy;
            nother.dummy.prev = tempNode2.next;
            dummy.prev = nother.dummy.prev;

            if (size - idx == nother.size) {
                return;
            } else if (size - idx < nother.size) {
                size += (other.size() - size);
            } else if (size > nother.size) {
                size += (size - other.size());
            }
        } else {
            Node cur = dummy;

            for (int i = 0; i < idx; i++) {
                cur = cur.next;
            }
            Node tempNode = cur.next;
            cur.next = dummy;
            dummy.prev = cur;
            this.size = idx + other.size();

            DLList<T> tempList = new DLList<T>();

            while (tempNode != dummy) {
                var t = tempNode.data;
                tempList.addBack(t);
                tempNode.prev.next = tempNode.next;
                tempNode.next.prev = tempNode.prev;
                tempNode = tempNode.next;
            }
            while (!other.isEmpty()) {
                var e = other.removeFront();
                this.addBack(e);
            }

            Node tempNode3 = tempList.dummy;
            for (int i = 0; i < tempList.size(); i++) {
                tempNode3 = tempNode3.next;
                other.addBack(tempNode3.data);
            }

        }

    }

    public void print() {
        Node tempNode = dummy.next;
        if (tempNode == null) {
            System.out.println("The List is Empty");
            return;
        }
        while (tempNode != dummy) {
            System.out.print(tempNode.data + " ");
            tempNode = tempNode.next;

        }
        System.out.print("\n");
        return;
    }

}
