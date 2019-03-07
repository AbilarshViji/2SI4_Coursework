package com.abilarsh;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int test[] = {2, 5, 4, 4, 3, 7, 8, 1};
        BSTSet a = new BSTSet(test);
        a.printBSTSet();
        System.out.println(a.isIn(3));
        System.out.println(a.isIn(5));
        System.out.println(a.height());
        System.out.println(a.size());
        a.add(2);
        a.add(49);
        a.printBSTSet();
        System.out.println(a.remove(2));
        a.printBSTSet();
        System.out.println(a.remove(4));
        a.printBSTSet();
        System.out.println(a.remove(5));
        a.printBSTSet();
        System.out.println(a.remove(999));
        a.printBSTSet();
        int test2[] = {999, 9999, 9, 1, 99999};
        BSTSet b = new BSTSet(test2);
        BSTSet c = a.union(b);
        c.printBSTSet();
        c = a.intersection(b);
        c.printBSTSet();
        c = a.difference(b);
        c.printBSTSet();
    }
}
