package com.abilarsh;

public class Main {

    public static void main(String[] args) {
        int test[] = {2, 5, 4, 4, 3, 1, 7, 8};
        BSTSet a = new BSTSet(test);
        a.printBSTset();
        System.out.println(a.isIn(3));
        System.out.println(a.isIn(5));
        System.out.println(a.height());
        System.out.println(a.remove(2));
        a.printBSTset();
        System.out.println(a.remove(4));
        a.printBSTset();
        System.out.println(a.remove(5));
        a.printBSTset();


    }
}
