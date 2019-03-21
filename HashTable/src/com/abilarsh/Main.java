package com.abilarsh;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        HashTableLin linTest = new HashTableLin(10, 1);
        linTest.insert(43);
        linTest.insert(79);
        linTest.insert(4);
        linTest.insert(241);
        System.out.println(linTest.getKeys());

        System.out.println(linTest.isIn(4));
        System.out.println(linTest.isIn(5));
        System.out.println();
        linTest.printKeys();
        System.out.println();
        linTest.insert(241); //testing duplicate
        linTest.printKeysAndIndexes();
        linTest.insert(465);
        linTest.insert(57);
        linTest.insert(487);
        linTest.insert(251);
        linTest.insert(483);
        linTest.insert(7);
        linTest.insert(40);
        linTest.insert(21);
        System.out.println(linTest.getSize());
        System.out.println(linTest.getKeys());
        System.out.println(linTest.getMaxKeys());


        HashTableQuad quadTest = new HashTableQuad(10, 1);
        quadTest.insert(43);
        quadTest.insert(79);
        quadTest.insert(4);
        quadTest.insert(241);
        System.out.println(quadTest.getKeys());
        System.out.println(quadTest.isIn(4));
        System.out.println(quadTest.isIn(5));
        System.out.println();
        quadTest.printKeys();
        System.out.println();
        quadTest.insert(241); //testing duplicate
        quadTest.printKeysAndIndexes();
        quadTest.insert(465);
        quadTest.insert(57);
        quadTest.insert(487);
        quadTest.insert(251);
        quadTest.insert(483);
        quadTest.insert(7);
        quadTest.insert(40);
        quadTest.insert(21);
        System.out.println(quadTest.getSize());
        System.out.println(quadTest.getKeys());
        System.out.println(quadTest.getMaxKeys());
        //successProbeSim();
        //failProbeSim();
    }

    public static void successProbeSim() {
        Random rand = new Random();
        int insert;
        HashTableLin linTest;
        HashTableQuad quadTest;
        double linSuccess;
        double quadSuccess;
        System.out.println("Success Probes (Sλ)");
        System.out.println("λ   Linear    Quadratic");
        for (int i = 1; i < 10; i++) {
            linSuccess = 0;
            quadSuccess = 0;
            for (int j = 0; j < 100; j++) {
                linTest = new HashTableLin(100000, i / 10.0);
                quadTest = new HashTableQuad(100000, i / 10.0);
                for (int k = 0; k < 100000; k++) {
                    insert = rand.nextInt(Integer.MAX_VALUE);
                    linSuccess += linTest.insertCount(insert);
                    quadSuccess += quadTest.insertCount(insert);
                }
                while (linTest.getKeys() != 100000) {
                    insert = rand.nextInt(Integer.MAX_VALUE);
                    linSuccess += linTest.insertCount(insert);
                }
                while (quadTest.getKeys() != 100000) {
                    insert = rand.nextInt(Integer.MAX_VALUE);
                    quadSuccess += quadTest.insertCount(insert);

                }
            }
            linSuccess = linSuccess / (100000 * 100);
            quadSuccess = quadSuccess / (100000 * 100);
            System.out.println(i / 10.0 + " " + linSuccess + " " + quadSuccess);
        }
    }

    public static void failProbeSim() {
        Random rand = new Random();
        int insert;
        int count;
        double linFail;
        HashTableLin linTest;
        System.out.println("Failed Probes (Uλ)");
        System.out.println("λ   Linear");
        for (int i = 1; i < 10; i++) {
            linTest = new HashTableLin(100000, i / 10.0);
            linFail = 0;
            count = 0;

            while (linTest.getKeys() != 100000) {
                insert = rand.nextInt(Integer.MAX_VALUE);
                linTest.insert(insert);
            }
            while (count <= 100000) {
                insert = rand.nextInt(Integer.MAX_VALUE);
                if (linTest.isInBonus(insert) != 0) {
                    count++;
                    linFail += linTest.isInBonus(insert);
                }
            }
            System.out.println(i / 10.0 + " " + linFail / 100000);
        }
    }
}
