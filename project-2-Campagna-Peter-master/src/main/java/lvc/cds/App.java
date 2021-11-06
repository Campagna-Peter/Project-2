package lvc.cds;

import ods.DLList;
import ods.Other;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        DLList<Integer> testList = new DLList<Integer>();

        for (int i = 0; i < 11; i++) {
            testList.addBack(i);
        }

        System.out.println("Printing list 1:");
        testList.print();
        System.out.println("Testing add() method:");
        testList.add(2, 6);
        testList.print();
        System.out.println("Testing remove() method:");    
        testList.remove(2);
        testList.print();
        System.out.println("Testing promote() method:");
        testList.promote(10);
        testList.print();
        System.out.println("Testing addFront() method:");
        testList.addFront(20);
        testList.print();
        System.out.println("Testing addBack() method:");
        testList.addBack(100);
        testList.print();
        System.out.println("Testing removeFront method:");
        testList.removeFront();
        testList.print();
        System.out.println("Testing removeBack() method:");
        testList.removeBack();
        testList.print();
        System.out.println("Testing set() method:");
        testList.set(1, 1000);
        testList.print();
        System.out.println("Testing find() method:");
        System.out.println(testList.find(1));
        System.out.println("Testing split method:");
        testList.split(5);
        testList.print();

        System.out.println();




        DLList<Integer> list = new DLList<Integer>();
        for (int i = 0; i < 11; i++) {
            list.addBack(i);
        }

        DLList<Integer> list2 = new DLList<Integer>();
        for (int i = 50; i < 61; i++) {
            list2.addBack(i);
        }

        DLList<Integer> list3 = new DLList<Integer>();
        for (int i = 0; i < 11; i++) {
            list3.addBack(i);
        }

        Other<Integer> list4 = new Other<Integer>();

        for (int i = 100; i < 111; i++) {
            list4.addBack(i);
        }

        System.out.println("Swapping Tail with DLList list2:");
        System.out.print("List 1: ");
        list.print();
        System.err.print("List 2: ");
        list2.print();
        list.swapTail(2, list2);
        System.out.println();
        System.out.print("Swapped List 1: ");
        list.print();
        System.out.print("Swapped List 2: ");
        list2.print();

        System.out.println();

        System.out.println("Swapping Tail with random list4:");
        System.out.print("List 3: ");
        list3.print();
        System.err.print("List 4: ");
        list4.print();
        list3.swapTail(5, list4);
        System.out.println();
        System.out.print("Swapped List 3: ");
        list3.print();
        System.out.print("Swapped List 4: ");
        list4.print();

    }
}
        