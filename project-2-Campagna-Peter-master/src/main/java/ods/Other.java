package ods;

public class Other<T> implements List<T> {

    class Node {
        T data;
        Node prev, next;
    }

    private int size;
    Node dummy;

    public Other() {
        dummy = new Node();
        dummy.next = dummy;
        dummy.prev = dummy;
        size = 0;
    }

    @Override
    // This would be O(n) because it has to iterate over the list and then assigns
    // references which is O(1). Therefore, it is O(n).
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
    // This would be O(n) because it has to iterate over the list and then assigns
    // references which is O(1). Therefore, it is O(n).
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
    // This would be O(n) because it has to iterate over the list and then assigns
    // references which is O(1). Therefore, it is O(n).
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
    // This would be O(1) because it is assiging references and then incrementing
    // size.
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
    // This would be O(1) because it is assiging references and then incrementing
    // size.
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
    // This would be O(1) because it is assiging references and then decrementing
    // size.
    public T removeFront() {
        Node tempNode = new Node();

        tempNode = dummy.next;
        dummy.next = tempNode.next;

        size--;
        return dummy.next.data;
    }

    @Override
    // This would be O(1) because it is assiging references and then decrementing
    // size.
    public T removeBack() {

        Node tempNode = new Node();

        tempNode = dummy.prev;
        tempNode.prev.next = dummy;
        dummy.prev = tempNode.prev;

        size--;
        return dummy.prev.data;
    }

    @Override
    // This would be O(n) because it has to iterate over the list and then assigns
    // references which is O(1). Therefore, it is O(n).
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
    // This would be O(1) because it is simply returning true if size == 0 false
    // otherwise.
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    // This would be O(1) because it is simply returning size.
    public int size() {
        return size;
    }

    @Override
    // This would be O(1) because it is simply assigning references.
    public void clear() {
        dummy.next = dummy;
        dummy.prev = dummy;
        size = 0;

    }

    @Override
    // This would be O(n) because it has to iterate through the list other than that it is O(1).
    public List<T> split(int idx) {
        Other<T> list = new Other<>();
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
    // This would be O(n) because it has to iterate over the list and then assigns a
    // reference, O(1) and calls another method that is O(1). Therefore, it is O(n).
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
    public void swapTail(int idx, List<T> other) {
        // TODO Auto-generated method stub
        if (other instanceof Other<?>) {
            Other nother = (Other<T>) other;

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
                cur = dummy.next;
                cur.next = dummy;
                dummy.prev = cur;
            }
            Node tempNode = cur.next;
            while (!other.isEmpty()) {
                var e = other.removeFront();
                this.addBack(e);
            }

            for (int x = 0; x < size; x++) {
                while (tempNode.next != null) {
                    var t = tempNode.next.data;
                    other.addBack(t);
                    tempNode = tempNode.next;
                }

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

    public static void main(String[] args) {
        DLList<Integer> list = new DLList<Integer>();

        for (int i = 0; i < 10; i++) {
            list.addBack(i);
        }

        list.print();
        var other = list.split(3);
        DLList<Integer> list2 = (DLList<Integer>) other;

        System.out.println("Original List");
        list.print();
        System.out.println();

        System.out.println("Original split");
        list2.print();
        System.out.println();

        System.out.println("Swapping Tail with list2");
        list.swapTail(7, list2);
        // DLList<Integer> list3 = (DLList<Integer>)other;

        list.print();
        list2.print();

    }

}