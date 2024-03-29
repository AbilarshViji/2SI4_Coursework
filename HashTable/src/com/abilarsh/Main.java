package com.abilarsh;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("LIN");

        HashTableLin linTest = new HashTableLin(5,  0.9);
        linTest.insert(43);
        linTest.insert(79);
        linTest.insert(4);
        linTest.insert(241);
        linTest.insert(241); //testing duplicate
        linTest.insert(465);
        linTest.insert(57);
        System.out.println(linTest.getKeys());
        System.out.println(linTest.isIn(4));
        System.out.println(linTest.isIn(5));
        linTest.printKeysAndIndexes();
        linTest.printKeys();
        System.out.println();
        System.out.println(linTest.getSize());
        System.out.println(linTest.getKeys());
        System.out.println(linTest.getMaxKeys());
        linTest.insert(487);
        linTest.insert(251);
        linTest.insert(483);
        linTest.insert(7);
        linTest.insert(40);
        linTest.printKeys();
        linTest.printKeysAndIndexes();
        System.out.println();
        System.out.println(linTest.getSize());
        System.out.println(linTest.getKeys());
        System.out.println(linTest.getMaxKeys());
      /*  linTest.insert(21);
        linTest.insert(231);
        linTest.insert(211);
        linTest.insert(2451);
        linTest.insert(2);
        linTest.insert(413);
        linTest.insert(411);
        linTest.insert(4451);
        linTest.insert(44);
        linTest.insert(1233);       */


        System.out.println("\nQUAD");
        HashTableQuad quadTest = new HashTableQuad(5, 0.9);
        quadTest.insert(43);
        quadTest.insert(79);
        quadTest.insert(4);
        quadTest.insert(241);
        quadTest.insert(241); //testing duplicate
        quadTest.insert(465);
        quadTest.insert(57);
        System.out.println(quadTest.getKeys());
        System.out.println(quadTest.isIn(4));
        System.out.println(quadTest.isIn(5));
        quadTest.printKeysAndIndexes();
        quadTest.printKeys();
        System.out.println();
        System.out.println(quadTest.getSize());
        System.out.println(quadTest.getKeys());
        System.out.println(quadTest.getMaxKeys());
        quadTest.insert(487);
        quadTest.insert(251);
        quadTest.insert(483);
        quadTest.insert(7);
        quadTest.insert(40);
        quadTest.printKeys();
        quadTest.printKeysAndIndexes();
        System.out.println();
        System.out.println(quadTest.getSize());
        System.out.println(quadTest.getKeys());
        System.out.println(quadTest.getMaxKeys());
        successProbeSim();
        failProbeSim();
    }

    public static void successProbeSim() {
        Random rand = new Random();
        int insert;
        HashTableLin linTest;
        HashTableQuad quadTest;
        double linSuccess;
        double quadSuccess;
        System.out.println("Success Probes (Sλ)");
        System.out.println("λ   Linear      Theoretical Quadratic");
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
            double linTheo =  Math.round(0.5*(1+(1/((1-i/10.0))))*10000)/10000.0;
            System.out.println(i / 10.0 + "\t" + linSuccess+"\t"+ linTheo + "\t\t" + quadSuccess);
        }
    }

    public static void failProbeSim() {
        Random rand = new Random();
        int insert;
        int count;
        double linFail;
        HashTableLin linTest;
        System.out.println("Failed Probes (Uλ)");
        System.out.println("λ   Linear   Theoretical");
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
            double theoretical = 0.5*(1+(1/((1-i/10.0)*(1-i/10.0))));
            System.out.println(i / 10.0 + "\t" + linFail / 100000 + "\t" + theoretical);
        }
    }
}
