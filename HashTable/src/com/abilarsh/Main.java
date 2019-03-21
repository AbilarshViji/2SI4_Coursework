package com.abilarsh;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        HashTableLin test = new HashTableLin(10, 1);
        test.insert(54);
        test.insert(65);
        test.insert(76);
        test.insert(87);
        test.printKeysAndIndexes();
        //  System.out.println("The number of keys: " + test.getKeys());
        // System.out.println("The average number of probes: " + avgProbes(test));
        //System.out.println("The average number of probes: "+avgProbesSim());
        avgProbesSim();
    }

    public static void avgProbesSim() {
        Random rand = new Random();
        int insert;
        HashTableLin linTest;
        HashTableQuad quadTest;
        double linProbe = 0;
        double quadProbe = 0;
        for (int i = 1; i < 10; i++) {
            linProbe = 0;
            quadProbe = 0;
            for (int j = 0; j < 100; j++) {
                linTest = new HashTableLin(100000, i / 10.0);
                quadTest = new HashTableQuad(100000, i / 10.0);
                for (int k = 0; k < 100000; k++) {
                    insert = rand.nextInt(Integer.MAX_VALUE);
                    linProbe += linTest.insertCount(insert);
                    quadProbe += quadTest.insertCount(insert);
                }
                while (linTest.getKeys() != 100000) {
                    insert = rand.nextInt(Integer.MAX_VALUE);
                    linProbe += linTest.insertCount(insert);
                }
                while (quadTest.getKeys() != 100000) {
                    insert = rand.nextInt(Integer.MAX_VALUE);
                    quadProbe += quadTest.insertCount(insert);

                }
            }
            linProbe = linProbe / (100000 * 100);
            quadProbe = quadProbe / (100000 * 100);
            System.out.println(linProbe + " " + quadProbe);
        }
    }

}
