package com.abilarsh;

import java.lang.Math;

public class HashTableLin {
    private Integer[] table;
    private int size;
    private int keys;
    private int maxKeys;
    private double maxLoad;

    public HashTableLin(int maxNum, double load) { //worst case n^2
        keys = 0;
        maxLoad = load;
        int minSize = (int) (maxNum / load);
        for (int i = minSize; i < Integer.MAX_VALUE; i++) {
            if (isPrime(i)) {
                size = i;
                break;
            }
        }
        maxKeys = (int) (size * maxLoad);
        table = new Integer[size];
    }

    public boolean isPrime(int n) { //log(n)
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void insert(int n) { //log(n) to n case dependent
        if (isIn(n)) {
            return;
        }
        keys++;
        if (keys > maxKeys) {
            rehash();
        }
        int index = n % size;
        if (table[index] == null) {
            table[index] = n;
        } else {
            while (table[index] != null) {
                index++;
                if (index >= (size - 1)) {
                    index = 0;
                }
            }
            table[index] = n;
        }
    }

    public int insertCount(int n) { //logn to n case dependent
        if (isIn(n)) {
            return -1;
        }
        int probe = 1;
        keys++;
        if (keys > maxKeys) {
            rehash();
        }
        int index = n % size;
        if (table[index] == null) {
            table[index] = n;
        } else {
            while (table[index] != null) {
                index++;
                probe++;
                if (index >= (size - 1)) {
                    index = 0;
                }
            }
            table[index] = n;
        }
        return probe;
    }

    public void rehash() { //n
        size *= 2;
        Integer[] duplicate = table;

        for (int i = size; i < Integer.MAX_VALUE; i++) {
            if (isPrime(i)) {
                size = i;
                break;
            }
        }
        maxKeys = (int) (size * maxLoad);
        table = new Integer[size];
        keys = 1;
        for (int i = 0; i < duplicate.length; i++) {
            if (duplicate[i] != null) {
                this.insert(duplicate[i]);
            }
        }
    }

    public boolean isIn(int n) { //n
        int index = n % size;
        boolean lastIndexCase = false;
        while (table[index] != null) {
            if (table[index] == n) {
                return true;
            } else {
                index++;
                if (index >= (size - 1)) {
                    index = 0;
                    if (lastIndexCase) {
                        return false;
                    }
                    lastIndexCase = true;
                } else if (index == n % size) {
                    return false;
                }
            }
        }
        return false;
    }

    public int isInBonus(int n) { //n
        int index = n % size;
        int count = 0;
        while (table[index] != null) {
            if (table[index] == n) {
                return 0;
            } else {
                index++;
                count++;
                if (index >= (size - 1)) {
                    index = 0;
                }
            }
        }
        return count;
    }

    public void printKeys() {
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.print(table[i] + ", ");
            }
        }
    }

    public void printKeysAndIndexes() {
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.println(i + ", " + table[i]);
            }
        }
    }

    public Integer[] getTable() {
        return table;
    }

    public int getSize() {
        return size;
    }

    public int getKeys() {
        return keys;
    }

    public int getMaxKeys() {
        return maxKeys;
    }

    public double getMaxLoad() {
        return maxLoad;
    }
}